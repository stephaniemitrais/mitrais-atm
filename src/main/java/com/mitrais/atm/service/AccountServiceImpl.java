package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;

public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepoImpl accountRepoImpl) {
		this.accountRepo = accountRepoImpl;
	}
	
	@Override
	public Long getAccountBalance(String accountNo) {

		Long balance = accountRepo.getAccount(accountNo).getBalance();
		
		return balance;
	}

	@Override
	public void setAccountBalance(String accountNo, Long balance) {

		Account account = accountRepo.getAccount(accountNo);
		account.setBalance(balance);
		
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
