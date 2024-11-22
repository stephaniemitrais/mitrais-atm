package com.mitrais.atm.transaction.repo;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.transaction.entity.Transaction;

public class TransactionRepoImpl implements TransactionRepo {
	private static final int MAX_TRANSACTIONS = 10;
    private static Map<String, LinkedBlockingDeque<Transaction>> transactionHist = new ConcurrentHashMap<>();

    @Override
    public void addTransaction(String accountNo, Transaction transaction) {
        transactionHist.computeIfAbsent(accountNo, k -> new LinkedBlockingDeque<>(MAX_TRANSACTIONS));
        BlockingDeque<Transaction> transactions = transactionHist.get(accountNo);

        if (transactions.size() == MAX_TRANSACTIONS) {
            transactions.pollFirst(); // Remove the oldest transaction
        }
        transactions.offerLast(transaction); // Add the new transaction
    }

    @Override
    public LinkedBlockingDeque<Transaction> getTransactionsByAccountNo(String accountNo) {
        return transactionHist.getOrDefault(accountNo, new LinkedBlockingDeque<>(MAX_TRANSACTIONS));
    }


}
