package com.mitrais.atm.transaction.view;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.view.ATMView;

public class TransactionHistoryView extends ATMView  {

	@Override
	public void display() {
		System.out.println("===================");
		System.out.println("Transaction History");
		System.out.println("===================");
		
	}

	public void displayDetails(LinkedBlockingDeque<Transaction> transactions) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");
		
		for(Transaction transaction : transactions) {
			System.out.println(transaction.getTransactionDatetime().format(formatter) + " | " + transaction.getTransactionType() + " | " + transaction.getAmount());
		}
		
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
	}
}
