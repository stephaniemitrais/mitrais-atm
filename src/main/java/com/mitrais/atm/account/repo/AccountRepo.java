package com.mitrais.atm.account.repo;

import java.util.Set;

import com.mitrais.atm.account.entity.Account;

public interface AccountRepo {

	Account getAccount(String accountNo, String password);

	Account getAccount(String accountNo);

	Set<Account> getAllAccounts();
	
	void addAccount(Account newAccount);
	
	void updateAccount(Account updatedAccount);
}