package com.mitrais.atm.service;

import java.util.List;

import com.mitrais.atm.AccountData;
import com.mitrais.atm.model.Account;

public class AccountRepoImpl implements AccountRepo {

    private static List<Account> accounts;
    
    public AccountRepoImpl() {
    	accounts = AccountData.getInstance().getAccounts();
	}

    @Override
	public Account getAccount(String accountNo, String password) {

    	Account account = accounts.stream()
    			.filter(acc -> accountNo.equals(acc.getAccountNo()) && password.equals(acc.getPassword()))
    			.findAny()                                      
                .orElse(null);  

    	return account;
    }
    
    @Override
	public Account getAccount(String accountNo) {

    	Account account = accounts.stream()
    			.filter(acc -> accountNo.equals(acc.getAccountNo()))
    			.findAny()                                      
                .orElse(null);  

    	return account;
    }

	@Override
	public Account updateAccount(Account account) {
		Account accountInRepo = getAccount(account.getAccountNo());
		
		accountInRepo.setBalance(account.getBalance());
		
		return accountInRepo;
	}	
	
		
}
