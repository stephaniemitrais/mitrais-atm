package com.mitrais.atm.transaction.service;

import java.util.List;

import com.mitrais.atm.transaction.entity.Transaction;

public interface TransactionService {
	public void addNewTransaction(String accountNo, Transaction transaction);
	
	public List<Transaction> getTransactionHistory(String accountNo);
	
	public List<Transaction> getTransactionHistory(String accountNo, int size);
}
