package com.mitrais.atm.account.repo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    
	// Update the balance for a specific account in the CSV file
    @Override
    public boolean updateBalance(String accountNumber, Long newBalance) {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(currentDir, csvFilePath).toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equals(accountNumber)) {
                    // Update balance if account number matches
                    data[3] = String.valueOf(newBalance); // Update balance (index 2)
                    line = String.join(",", data); // Reconstruct the updated line
                    updated = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        // If the account is found and updated, write the lines back to the file
        if (updated) {
        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(currentDir, csvFilePath).toString()))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                } 
                
            } catch (IOException e) {
                System.out.println("Error writing to CSV file: " + e.getMessage());
            }
        	
            try {
				updateAccountData();
			} catch (IOException e) {
				System.out.println("Error refreshing CSV file: " + e.getMessage());
			}
        }

        return updated;
    }
    
    private void updateAccountData() throws IOException {
    	accounts.clear();
    	loadAccountsFromCSV();
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
