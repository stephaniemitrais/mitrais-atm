package com.mitrais.atm;

import com.mitrais.atm.controlller.HomeController;
import com.mitrais.atm.controlller.LoginController;


public class ATM {
	
	private static final SessionManager sessionManager = new SessionManager();

	public ATM() {
       
	}
	
	public static void main(String[] args) {
		
	    boolean atmIsRunning = true;

	    LoginController loginController = new LoginController(sessionManager, null);
	    
	    while (atmIsRunning) {     	   
	    	LoginUser loginUser = loginController.displayLogin();
	    	if (loginUser != null) {

            	String sessionId = SessionManager.createSession(loginUser);
            	
            	// go to next main menu view
    	    	HomeController homeController = new HomeController(sessionManager, sessionId);
    	    	homeController.displayMainMenu();
	    	}
	    	
        }

	}
	    	
	
}

