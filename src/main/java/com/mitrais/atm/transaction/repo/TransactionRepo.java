package com.mitrais.atm.transaction.repo;

import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.transaction.entity.Transaction;

public interface TransactionRepo {
	public LinkedBlockingDeque<Transaction> getTransactionsByAccountNo(String accountNo);
	
	public void addTransaction(String accountNo, Transaction transaction);
    
}
