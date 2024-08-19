package com.mitrais.atm.service;

import com.mitrais.atm.model.Account;

public interface AccountRepo {

	Account getAccount(String accountNo, String password);

	Account getAccount(String accountNo);
	
	Account updateAccount(Account account);

}