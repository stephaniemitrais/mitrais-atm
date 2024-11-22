package com.mitrais.atm.transaction.service;

import java.time.LocalDateTime;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.service.AccountService;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.entity.TransactionType;
import com.mitrais.atm.transaction.entity.Withdrawal;
import com.mitrais.atm.transaction.repo.TransactionRepo;
import com.mitrais.atm.transaction.validation.WithdrawException;

public class WithdrawServiceImpl extends TransactionServiceImpl implements WithdrawService {

	private AccountService accountService;
	
	public WithdrawServiceImpl(AccountServiceImpl accountServiceImpl, TransactionRepo transactionRepo) {
		super(transactionRepo);
		this.accountService = accountServiceImpl;
	}
	@Override
	public void withdraw(String accountNo, Long withdrawAmount) throws WithdrawException {
		
		Account account = accountService.getAccountByAccountNo(accountNo);
		
		Long balance = account.getBalance();
		if(withdrawAmount > 1000) {
			throw new WithdrawException("Maximum amount to withdraw is $1000");
		}
		
		if(balance < withdrawAmount) {
			throw new WithdrawException("Insufficient balance: $" + balance);
		}
		
		accountService.setAccountBalance(accountNo, account.getBalance() - withdrawAmount);
		
		
		Transaction withdraw = new Withdrawal();
		withdraw.setTransactionDatetime(LocalDateTime.now());
		withdraw.setAccount(account);
		withdraw.setAmount(withdrawAmount);
		withdraw.setTransactionType(TransactionType.WITHDRAW);
		addNewTransaction(accountNo, withdraw);
	}
	
}
