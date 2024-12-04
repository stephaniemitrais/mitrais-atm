package com.mitrais.atm.transaction.service;

import java.time.LocalDateTime;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.service.AccountService;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.entity.TransactionType;
import com.mitrais.atm.transaction.entity.Transfer;
import com.mitrais.atm.transaction.repo.TransactionRepo;
import com.mitrais.atm.transaction.validation.TransferException;

public class TransferServiceImpl extends TransactionServiceImpl implements TransferService {

	private AccountService accountService;
	
	public TransferServiceImpl(AccountServiceImpl accountServiceImpl, TransactionRepo transactionRepo) {
		super(transactionRepo);
		this.accountService = accountServiceImpl;
	}
	
	@Override
	public Transfer createNewTransfer(String destinationAccNo, String sourceAccNo, Long amount) throws TransferException {
		
		validateTransferAccount(destinationAccNo, sourceAccNo);
		validateTransferAmount(sourceAccNo, amount);

		Account fromAccount = accountService.getAccountByAccountNo(sourceAccNo);
		Account toAccount = accountService.getAccountByAccountNo(destinationAccNo);
		
		fromAccount.deductBalance(amount);
		toAccount.addBalance(amount);

		accountService.updateAccount(fromAccount);
		accountService.updateAccount(toAccount);
			
		Transfer transfer = new Transfer();
		transfer.setAmount(amount);
		
		transfer.setAccount(fromAccount);
		transfer.setToAccount(toAccount);
		
		transfer.setTransactionDatetime(LocalDateTime.now());
		transfer.setAmount(amount);
		transfer.setTransactionType(TransactionType.TRANSFER);
		addNewTransaction(fromAccount.getAccountNo(), transfer);
		
		return transfer;
	}

	@Override
	public void validateTransferAccount(String destinationAccNo, String sourceAccNo) throws TransferException {
		if(destinationAccNo.length() != 0 && !destinationAccNo.chars().allMatch(Character::isDigit)) {
			throw new TransferException("Invalid Destination Account No");
		}
		
		if(destinationAccNo.equals(sourceAccNo)) {
			throw new TransferException("Destination Account No must be different from Source Account No");
		}
		
		Account toAccount = accountService.getAccountByAccountNo(destinationAccNo);
		
		if(toAccount == null) {
			throw new TransferException("Invalid Destination Account No");
		}

	}
	
	public void validateTransferAmount(String sourceAccNo, Long amount) throws TransferException {
		
		Account fromAccount = accountService.getAccountByAccountNo(sourceAccNo);
		
		Long balance = fromAccount.getBalance();

		if(balance < amount) {
			throw new TransferException("Insufficient balance: $" + balance);
		}
	}
}
