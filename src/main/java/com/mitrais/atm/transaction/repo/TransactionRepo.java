package com.mitrais.atm.transaction.repo;

import java.util.List;

import com.mitrais.atm.transaction.entity.Transaction;

public interface TransactionRepo {
	public List<Transaction> getTransactionsByAccountNo(String accountNo);
	
	public void addTransaction(String accountNo, Transaction transaction);
    
}
