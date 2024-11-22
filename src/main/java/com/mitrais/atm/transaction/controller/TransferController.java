package com.mitrais.atm.transaction.controller;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.service.AccountService;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.controlller.BaseController;
import com.mitrais.atm.transaction.entity.Transfer;
import com.mitrais.atm.transaction.service.TransferService;
import com.mitrais.atm.transaction.service.TransferServiceImpl;
import com.mitrais.atm.transaction.validation.TransferException;
import com.mitrais.atm.transaction.view.TransactionSummary;
import com.mitrais.atm.transaction.view.TransferSummary;
import com.mitrais.atm.transaction.view.TransferView;
import com.mitrais.atm.util.UniqueRandomNumberGenerator;

public class TransferController extends BaseController {

	private TransferView view;
	
	private TransferService service;
	
	private AccountService accountService;
	
	public TransferController(SessionManager sessionManager, String sessionId, TransferView transferView, TransferServiceImpl transferServiceImpl, AccountServiceImpl accountServiceImpl) {
		super(sessionManager, sessionId);
		this.view = transferView;
		this.service = transferServiceImpl;
		this.accountService = accountServiceImpl;
	}
	
	public void displayTransfer() {
		view.display();
		
		String destinationAccountNo;
		
		String referenceNo = "";
		
		Long amount = Long.valueOf(0);
		
		Session session = sessionManager.getSession(getSessionId());
		
		LoginUser loginUser = session.getLoginUser();
		
		Account loginAccount = loginUser.getAccount();
		
		String sourceAccountNo = loginAccount.getAccountNo();
		
   	    boolean successTransfer = false;
   	 
		transfer:
		while(true) {
			//enter destination account
			while (true) {
			    destinationAccountNo = view.getInput("Please enter destination account and press enter to continue or \r\n"
	    				+ "press enter to go back to Transaction: ");
	    	    
	    	    if (destinationAccountNo.length() == 0) {
	    	    	break transfer;
	    	    }
	    	    
	    	    try {
	    	    	 service.validateTransferAccount(destinationAccountNo, sourceAccountNo);
	    		} catch (TransferException e) {
	    			view.displayMessage(e.getMessage());
	    			continue;
	    		}
	    	   			    	
		    	break;
			 }
			
			// enter amount
			while (true) {

	    	    String amountInput = view.getInput("Please enter transfer amount and \r\n"
	    				+ "press enter to continue or \r\n"
	    				+ "press enter to go back to Transaction:");
	    	    
	    	    if (amountInput.length() == 0) {
	    	    	break transfer;
	    	    }
	    	    
	    	    amount = Long.valueOf(amountInput);
	    	    
		    	if (!amountInput.chars().allMatch(Character::isDigit)) {
		    		view.displayMessage("Invalid amount");
		    		continue;
		    	}
		    	
		    	try {
	    	    	 service.validateTransferAmount(sourceAccountNo, Long.valueOf(amountInput));
	    	    	 
	    			 // confirm inputted destinationAccountNo and Amount
	    	    	 
	    	    	 referenceNo = UniqueRandomNumberGenerator.generateUniqueRandomNumber();
	    	    	 
	        		 view.displayTransferConfirm(destinationAccountNo, amount, referenceNo);
	        		 
	        		 String confirm = view.getInput("Please choose option[2]:");
	        			 
	        		 switch (confirm) {
	        		 case "1":
	        			 transfer(destinationAccountNo, sourceAccountNo, amount);
	        			 successTransfer = true;
	        			 break transfer;
	        		 case "2":
	        			 break transfer;
	        		 default:
	        			 break transfer;
	        		 }
	        		 
	    		} catch (TransferException e) {
	    			view.displayMessage(e.getMessage());
	    			continue;
	    		}
			    	
			 }
	    	 
		}
		
		// ask for next step
		if(successTransfer) {
			
			TransactionSummary summary = new TransferSummary(destinationAccountNo, amount, referenceNo, accountService.getAccountBalance(sourceAccountNo));
			
			summary.displaySummary("Transfer successfully");

			String userInput = view.getInput("Please choose option [2] :");
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
	
	
	private boolean transfer(String destinationAccountNo, String sourceAccountNo, Long amount) {

		boolean isSuccess = false;
		
		try {
			Transfer transfer = service.createNewTransfer(destinationAccountNo, sourceAccountNo, amount);
			
			if (transfer != null) {
				isSuccess = true;
			} else {
				isSuccess  = false;
			}
			
			return isSuccess;
			
		} catch (TransferException e) {
			view.displayMessage(e.getMessage());
			
			return isSuccess;
		}

	}
	
}
