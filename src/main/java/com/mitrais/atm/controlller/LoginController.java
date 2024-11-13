package com.mitrais.atm.controlller;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.service.LoginService;
import com.mitrais.atm.service.LoginServiceImpl;
import com.mitrais.atm.view.LoginView;

public class LoginController extends BaseController {

	private LoginView view;

	private LoginService loginService;
		
    public LoginController(SessionManager sessionManager, String sessionId, LoginView loginView, LoginServiceImpl loginServiceImpl) {
    	super(sessionManager, sessionId);
        this.view = loginView;
        this.loginService = loginServiceImpl;
    }
    
    public LoginUser displayLogin() {
        String accountNo = "";
        String pin = "";
        boolean validLogin = false;
        
    	view.display();
    	
        do {

            accountNo = view.getAccountNo();

            if (!validateAccountNo(accountNo)) {
            	continue;
            }
            
            pin = view.getPin();
            
            if (!validatePIN(pin)) {
            	continue;
            }

            
            if (!validateAuthentication(accountNo, pin)) {
            	continue;
            }
            
            validLogin = true;
            

        } while (!validLogin); // Exit
        

    	LoginUser user = loginService.getLoginUser(accountNo, pin);
    	
        return user;
    }
    
    private boolean validateAccountNo(String accountNumberInput) {
		
	    if(accountNumberInput.length() != 6) {
	    	view.displayMessage("Account Number should have 6 digits length");
	    	return false;
	    } 
	    
    	if (!accountNumberInput.chars().allMatch(Character::isDigit)) {
    		view.displayMessage("Account Number should only contains numbers");
    		return false;
    	}
    	
 	    return true;
	}
	    
	
	private boolean validatePIN(String pinInput) {
		
		if (pinInput.length() != 6) {
			view.displayMessage("PIN should have 6 digits length");
 	    	return false;
 	    } 
 	    if (!pinInput.chars().allMatch(Character::isDigit)) {
 	    	view.displayMessage("PIN should only contains numbers");
 	    	return false;
 	    }
 	    
 	    return true;
	}
	
	private boolean validateAuthentication(String accountNumberInput, String pinInput) {
		boolean isValid = loginService.authenticate(accountNumberInput, pinInput);
		
		if (!isValid) {
			view.displayMessage("Invalid Account Number/PIN");
		}
		
		return isValid;
	}
}
