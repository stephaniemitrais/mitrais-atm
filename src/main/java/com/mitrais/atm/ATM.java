package com.mitrais.atm;

import java.io.IOException;

import com.mitrais.atm.account.controller.LoginController;
import com.mitrais.atm.account.repo.AccountRepoImpl;
import com.mitrais.atm.account.service.LoginServiceImpl;
import com.mitrais.atm.controlller.HomeController;
import com.mitrais.atm.view.HomeView;
import com.mitrais.atm.view.LoginView;


public class ATM {
	
	private static final SessionManager sessionManager = new SessionManager();

	public ATM() {
       
	}
	
	public static void main(String[] args) {
		
	    boolean atmIsRunning = true;
        
	    LoginController loginController;

		try {
			loginController = new LoginController(sessionManager, null, new LoginView(), new LoginServiceImpl(new AccountRepoImpl()));
			
			 while (atmIsRunning) {     	   
			    	LoginUser loginUser = loginController.displayLogin();
			    	if (loginUser != null) {

		            	String sessionId = SessionManager.createSession(loginUser);
		            	
		            	// go to next main menu view
		    	    	HomeController homeController = new HomeController(sessionManager, sessionId, new HomeView());
		    	    	homeController.displayMainMenu();
			    	}
			    	
		        }
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	    	
	
}

