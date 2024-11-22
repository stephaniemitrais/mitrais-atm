package com.mitrais.atm.account.service;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.repo.AccountRepo;

public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}
	
	@Override
	public Long getAccountBalance(String accountNo) {

		Long balance = accountRepo.getAccount(accountNo).getBalance();
		
		return balance;
	}

	@Override
	public void setAccountBalance(String accountNo, Long balance) {
		
		accountRepo.updateBalance(accountNo, balance);
		
	}
	
	
	@Override
	public Account getAccountByAccountNo(String accountNo) {
		
		Account account = accountRepo.getAccount(accountNo);
		
		return account;
		
	}

	@Override
	public Account getAccountByAccountNoAndPin(String accountNo, String pin) {
		Account account = accountRepo.getAccount(accountNo, pin);
		
		return account;
	}


}
