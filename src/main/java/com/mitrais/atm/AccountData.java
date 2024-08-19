package com.mitrais.atm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mitrais.atm.model.Account;

public class AccountData {
    private static final AccountData instance = new AccountData();
    private final List<Account> accounts = new ArrayList<>(Arrays.asList(
            new Account("John Doe", "112233", "012108", Long.valueOf(100)),
            new Account("Jane Doe", "112244", "932012", Long.valueOf(30))
    ));

    private AccountData() {}

    public static AccountData getInstance() {
        return instance;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts); // Return a copy to prevent direct modification
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void updateAccount(Account account) {
    	
    	Account storedAccount = accounts.stream()
    			.filter(acc -> account.getAccountNo().equals(acc.getAccountNo()))
    			.findAny()                                      
                .orElse(null);  
    	
    	storedAccount.setBalance(account.getBalance());
    	
    }

}