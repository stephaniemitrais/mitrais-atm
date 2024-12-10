package com.mitrais.atm.transaction.service;

import java.time.LocalDateTime;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.service.AccountService;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.entity.TransactionType;
import com.mitrais.atm.transaction.entity.Withdrawal;
import com.mitrais.atm.transaction.repo.TransactionRepo;

public class WithdrawServiceImpl extends TransactionServiceImpl implements WithdrawService {

	private AccountService accountService;
	
	public WithdrawServiceImpl(AccountServiceImpl accountServiceImpl, TransactionRepo transactionRepo) {
		super(transactionRepo);
		this.accountService = accountServiceImpl;
	}
	
	@Override
	public void withdraw(String accountNo, Long withdrawAmount) {
		Account account = accountService.getAccountByAccountNo(accountNo);
		
		account.deductBalance(withdrawAmount);

		accountService.updateAccount(account);
		
		Transaction withdraw = new Withdrawal();
		withdraw.setTransactionDatetime(LocalDateTime.now());
		withdraw.setAccount(account);
		withdraw.setAmount(withdrawAmount);
		withdraw.setTransactionType(TransactionType.WITHDRAW);
		addNewTransaction(accountNo, withdraw);
	}
	

	
}
