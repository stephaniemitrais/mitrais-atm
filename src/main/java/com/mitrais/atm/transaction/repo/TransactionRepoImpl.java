package com.mitrais.atm.transaction.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.mitrais.atm.transaction.entity.Transaction;

public class TransactionRepoImpl implements TransactionRepo {

    private static Map<String, ArrayList<Transaction>> transactionHist = new ConcurrentHashMap<>();

    @Override
    public void addTransaction(String accountNo, Transaction transaction) {
        transactionHist.computeIfAbsent(accountNo, k -> new ArrayList<>());
        
        ArrayList<Transaction> transactions = transactionHist.get(accountNo);

        transactions.add(transaction); // Add the new transaction
    }

    @Override
    public List<Transaction> getTransactionsByAccountNo(String accountNo) {
    	ArrayList<Transaction> transactions = transactionHist.getOrDefault(accountNo, new ArrayList<>());
        List<Transaction> transList = transactions.stream().collect(Collectors.toList());
    	return transList;
    }


}
