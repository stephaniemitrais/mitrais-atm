package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;

public interface AccountService {

	public Long getAccountBalance(String accountNo);
	
	public void setAccountBalance(String accountNo, Long balance);
	
	public Account getAccountByAccountNo(String accountNo);
	
	public Account getAccountByAccountNoAndPin(String accountNo, String pin);
	
}
