package com.mitrais.atm.account.service;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.repo.AccountRepo;

public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	@Override
	public void updateAccount(Account account) {
		
		accountRepo.updateAccount(account);
		
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
