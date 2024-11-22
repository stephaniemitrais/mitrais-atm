package com.mitrais.atm.transaction.service;

import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.repo.TransactionRepo;

public class TransactionServiceImpl implements TransactionService {
	
	protected TransactionRepo transactionRepo;

	public TransactionServiceImpl(TransactionRepo transactionRepo) {
		this.transactionRepo = transactionRepo;
	}

	@Override
	public void addNewTransaction(String accountNo, Transaction transaction) {
		// TODO Auto-generated method stub
		transactionRepo.addTransaction(accountNo, transaction);
	}

	@Override
	public LinkedBlockingDeque<Transaction> getTransactionHistory(String accountNo) {
		
		LinkedBlockingDeque<Transaction> transactions = transactionRepo.getTransactionsByAccountNo(accountNo);
		
		return transactions;
		
	}

}
