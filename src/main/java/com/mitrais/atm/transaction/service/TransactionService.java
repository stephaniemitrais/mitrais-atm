package com.mitrais.atm.transaction.service;

import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.transaction.entity.Transaction;

public interface TransactionService {
	public void addNewTransaction(String accountNo, Transaction transaction);
	
	public LinkedBlockingDeque<Transaction> getTransactionHistory(String accountNo);
}
