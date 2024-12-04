package com.mitrais.atm.account.service;

import com.mitrais.atm.account.entity.Account;

public interface AccountService {
	
	public void updateAccount(Account account);
	
	public Account getAccountByAccountNo(String accountNo);
	
	public Account getAccountByAccountNoAndPin(String accountNo, String pin);
	
}
