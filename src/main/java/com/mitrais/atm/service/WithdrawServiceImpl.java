package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;
import com.mitrais.atm.validation.WithdrawException;

public class WithdrawServiceImpl implements WithdrawService {

	private AccountService accountService;
	
	public WithdrawServiceImpl(AccountServiceImpl accountServiceImpl) {
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
		
	}
	
}
