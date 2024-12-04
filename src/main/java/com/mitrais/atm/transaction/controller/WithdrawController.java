package com.mitrais.atm.transaction.controller;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.service.AccountService;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.controlller.BaseController;
import com.mitrais.atm.transaction.service.WithdrawService;
import com.mitrais.atm.transaction.service.WithdrawServiceImpl;
import com.mitrais.atm.transaction.validation.WithdrawException;
import com.mitrais.atm.transaction.view.TransactionSummary;
import com.mitrais.atm.transaction.view.WithdrawView;
import com.mitrais.atm.transaction.view.WithdrawalSummary;

public class WithdrawController extends BaseController {

	private WithdrawView view;
	
	private WithdrawService service;
	
	private AccountService accountService;

	public WithdrawController(SessionManager sessionManager, String sessionId, WithdrawView withdrawView, WithdrawServiceImpl withdrawServiceImpl, AccountServiceImpl accountServiceImpl) {
		super(sessionManager, sessionId);
		this.view = withdrawView;
		this.service = withdrawServiceImpl;
		this.accountService = accountServiceImpl;
	}
	
	public void displayWithdraw() {
		Long amount = Long.valueOf(0);
		
		boolean successWithdraw = false;
		
		//withdrawal transaction
		withdrawView: while(true)
		{
            view.display();
            String option = view.getInput("Please choose option[5]:");
            switch (option) {
			case "1": //$10
				amount = Long.valueOf(10);
				if (withdraw(amount)) {
					successWithdraw = true;
					break withdrawView;
				} else {
					continue;
				}
				
			case "2"://$50
				amount = Long.valueOf(50);
				if (withdraw(amount)) {
					successWithdraw = true;
					break withdrawView;
				} else {
					continue;
				}
			case "3"://$100
				amount = Long.valueOf(100);
				if (withdraw(amount)) {
					successWithdraw = true;
					break withdrawView;
				} else {
					continue;
				}
			case "4"://other withdraw

				otherAmount: while (true) {
					String otherAmount = view.getInput("Enter amount to withdraw:");
					
					if(otherAmount.length() == 0) break otherAmount;
					
					if(isValidAmount(otherAmount)) {
						amount = Long.valueOf(otherAmount);
						
						if(!withdraw(amount)) continue;
						else {
							successWithdraw = true;
							break withdrawView;
						}
					} else {
						continue;
					}
				}
				break;
			case "5"://back
				break withdrawView;
			default://exit
				break withdrawView;
			}
        }
		
		// ask for next step
		if(successWithdraw) {
			Session session = sessionManager.getSession(getSessionId());
			
			LoginUser user = session.getLoginUser();
			
			Account loginAccount = user.getAccount();
			
			TransactionSummary summary = new WithdrawalSummary(amount, accountService.getAccountByAccountNo(loginAccount.getAccountNo()).getBalance());
			
			summary.displaySummary("Withdraw successfully");

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

	private boolean withdraw(Long withdrawAmount) {

		Session session = sessionManager.getSession(getSessionId());
			
		LoginUser user = session.getLoginUser();
		
		String accountNo = user.getAccount().getAccountNo();
		
		boolean isSuccess = false;
		
		try {
			service.withdraw(accountNo, withdrawAmount);
			
			isSuccess = true;
			
			return isSuccess;
		} catch (WithdrawException e) {
			view.displayMessage(e.getMessage());
			
			return isSuccess;
		}

	}
	
	private boolean isValidAmount(String inputtedOtherAmount) {
		if (!inputtedOtherAmount.chars().allMatch(Character::isDigit)) {
			view.displayMessage("Invalid ammount");
    		return false;
    	} 
    	return true;
	}
	
}
