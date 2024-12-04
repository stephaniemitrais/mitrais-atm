package com.mitrais.atm.transaction.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.repo.TransactionRepo;

public class TransactionServiceImpl implements TransactionService {
	
	protected TransactionRepo transactionRepo;

	public TransactionServiceImpl(TransactionRepo transactionRepo) {
		this.transactionRepo = transactionRepo;
	}

	@Override
	public void addNewTransaction(String accountNo, Transaction transaction) {
		transactionRepo.addTransaction(accountNo, transaction);
	}

	@Override
	public List<Transaction> getTransactionHistory(String accountNo) {
		
		List<Transaction> transactions = transactionRepo.getTransactionsByAccountNo(accountNo);
		
		return transactions;
		
	}
	
	@Override
	public List<Transaction> getTransactionHistory(String accountNo, int size) {
		
		List<Transaction> transactions = transactionRepo.getTransactionsByAccountNo(accountNo);
		transactions.sort(Comparator.comparing(Transaction::getTransactionDatetime).reversed());
		List<Transaction> limitedTransacations = transactions.stream().limit(size).collect(Collectors.toList());
		
		return limitedTransacations;
		
	}

}
