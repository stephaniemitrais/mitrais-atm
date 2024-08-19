package com.mitrais.atm.controlller;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.model.Account;
import com.mitrais.atm.service.AccountService;
import com.mitrais.atm.service.AccountServiceImpl;
import com.mitrais.atm.service.WithdrawService;
import com.mitrais.atm.service.WithdrawServiceImpl;
import com.mitrais.atm.validation.WithdrawException;
import com.mitrais.atm.view.TransactionSummary;
import com.mitrais.atm.view.WithdrawView;
import com.mitrais.atm.view.WithdrawalSummary;

public class WithdrawController extends BaseController {

	private WithdrawView view;
	
	private WithdrawService service;
	
	private AccountService accountService;

	public WithdrawController(SessionManager sessionManager, String sessionId) {
		super(sessionManager, sessionId);
		this.view = new WithdrawView();
		this.service = new WithdrawServiceImpl();
		this.accountService = new AccountServiceImpl();
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
				String otherAmount = view.getInput("Enter amount to withdraw:");
				
				amount = Long.valueOf(otherAmount);
				
				if(otherAmount.length() == 0) break;
				
				boolean validateOtherAmount = true;
				
				while (validateOtherAmount) {
					if(!validateOtherAmount(otherAmount)) {
						continue;
					} else {
						if(!withdraw(Long.valueOf(otherAmount))) continue;
						else {
							successWithdraw = true;
							validateOtherAmount = false;
							break withdrawView;
						}
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
			
			TransactionSummary summary = new WithdrawalSummary(amount, accountService.getAccountBalance(loginAccount.getAccountNo()));
			
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
	
	private boolean validateOtherAmount(String inputtedOtherAmount) {
		if (!inputtedOtherAmount.chars().allMatch(Character::isDigit)) {
			view.displayMessage("Invalid ammount");
    		return false;
    	} 
    	return true;
	}
	
}
