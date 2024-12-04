package com.mitrais.atm.account.repo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.util.ConfigLoader;

public class AccountRepoImpl implements AccountRepo {

	private static Set<Account> accounts =  Collections.synchronizedSet(new HashSet<>());
	
	private static final String csvFilePath = ConfigLoader.getInstance().getProperty("accountCsvFilePath");
    
	private static final String currentDir = System.getProperty("user.dir");  // Get current working directory

	public AccountRepoImpl() {
		loadAccountsFromCSV();
	}
	
    public static void loadAccountsFromCSV() {
     
        List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(currentDir, csvFilePath));
			
			Set<String> accountNumbers = new HashSet<>();

	        lines.stream().forEach(line -> {
	       
	            String[] data = line.split(",");
	            String name = data[0];
	            String accountNumber = data[1];
	            String pin = data[2];
	            Long balance = Long.parseLong(data[3]);
	           
	            if (!accountNumbers.add(accountNumber)) {
	                throw new IllegalArgumentException("Duplicate account number: " + accountNumber);
	            }

	            Account account = new Account(name, accountNumber, pin, balance);
	            accounts.add(account);
	        });
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

    // Method to update an existing account
    @Override
    public void updateAccount(Account updatedAccount) {

        // Find and remove the old account
        accounts.removeIf(account -> account.getAccountNo().equals(updatedAccount.getAccountNo()));
        // Add the updated account
        accounts.add(updatedAccount);
        
    }
    
    // Method to add a new account
    @Override
    public void addAccount(Account newAccount) {

        accounts.add(newAccount);

    }

    
    @Override
    public Set<Account> getAllAccounts() {
        synchronized (accounts) {
            return new HashSet<>(accounts); // Return a copy to maintain thread safety
        }
    }
   
   
    @Override
	public Account getAccount(String accountNo, String password) {

    	Account account = accounts.stream()
    			.filter(acc -> accountNo.equals(acc.getAccountNo()) && acc.validatePassword(password))
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


		
}
