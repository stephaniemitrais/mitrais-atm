package com.mitrais.atm.account.service;

import com.mitrais.atm.account.entity.Account;

public interface AccountService {

	public Long getAccountBalance(String accountNo);
	
	public void setAccountBalance(String accountNo, Long balance);
	
	public Account getAccountByAccountNo(String accountNo);
	
	public Account getAccountByAccountNoAndPin(String accountNo, String pin);
	
}
