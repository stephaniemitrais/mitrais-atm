package com.mitrais.atm.account.repo;

import com.mitrais.atm.account.entity.Account;

public interface AccountRepo {

	Account getAccount(String accountNo, String password);

	Account getAccount(String accountNo);
	
	Account updateAccount(Account account);
	
	boolean updateBalance(String accountNo, Long newBalance);

}