package com.mitrais.atm.transaction.controller;

import java.util.concurrent.LinkedBlockingDeque;

import com.mitrais.atm.SessionManager;
import com.mitrais.atm.controlller.BaseController;
import com.mitrais.atm.transaction.entity.Transaction;
import com.mitrais.atm.transaction.service.TransactionService;
import com.mitrais.atm.transaction.view.TransactionHistoryView;

public class TransactionController extends BaseController {
	private TransactionService transactionService;
	
	private TransactionHistoryView transactionView;
	
	public TransactionController(SessionManager sessionManager, String sessionId, TransactionService transactionService, TransactionHistoryView transactionView) {
		super(sessionManager, sessionId);
		this.transactionService = transactionService;
		this.transactionView = transactionView;
	}


	public void displayTransactionHistory() {
		transactionView.display();
		
		String accountNo = getLoginUserAccountNo();
		
		LinkedBlockingDeque<Transaction> trans = transactionService.getTransactionHistory(accountNo);
		
		transactionView.displayDetails(trans);
		
		String userInput = transactionView.getInput("Please choose option [2] :");
		switch (userInput) {
			case "1": //back to main menu	
				break;
			case "2"://exit
				sessionManager.invalidateSession(getSessionId());
				break;		
			default://exit
				sessionManager.invalidateSession(getSessionId());
				break;
		}
		
		

	}
}
 