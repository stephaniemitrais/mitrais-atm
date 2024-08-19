package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.model.Transfer;
import com.mitrais.atm.validation.TransferException;

public class TransferServiceImpl implements TransferService {

	private AccountService accountService;
	
	public TransferServiceImpl() {
		this.accountService = new AccountServiceImpl();
	}
	
	@Override
	public Transfer createNewTransfer(String destinationAccNo, String sourceAccNo, Long amount) throws TransferException {
		
		Transfer transfer = null;
		
		validateTransferAccount(destinationAccNo, sourceAccNo);
		validateTransferAmount(sourceAccNo, amount);
		
		transfer = new Transfer();
		transfer.setAmount(amount);

		Account fromAccount = accountService.getAccountByAccountNo(sourceAccNo);
		Account toAccount = accountService.getAccountByAccountNo(destinationAccNo);
		
		Long sourceBalance = fromAccount.getBalance() - amount;
		Long destinationBalance = toAccount.getBalance() + amount;
		
		accountService.setAccountBalance(fromAccount.getAccountNo(), sourceBalance);
		accountService.setAccountBalance(toAccount.getAccountNo(), destinationBalance);
		
		transfer.setFromAccount(fromAccount);
		transfer.setToAccount(toAccount);
		
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
