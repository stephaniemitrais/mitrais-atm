package com.mitrais.atm.controlller;

import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.view.HomeView;

public class HomeController extends BaseController {

	private HomeView view;
	
	public HomeController(SessionManager sessionManager, String sessionId) {
		super(sessionManager, sessionId);
		this.view = new HomeView();
	}

	public void displayMainMenu() {
              
        String menu;
        boolean loggedin = true;
        while(loggedin) {
            view.display();

            menu = view.getInput("Please choose option [4]:");

            switch (menu) {
    		case "1"://Withdraw
    			WithdrawController withdrawController = new WithdrawController(sessionManager, sessionId);
    			withdrawController.displayWithdraw();
    			if(isLogout()) loggedin = false;
    			break;
    		case "2"://Fund Transfer
    			TransferController transferController = new TransferController(sessionManager, sessionId);
    			transferController.displayTransfer();
    			if(isLogout()) loggedin = false;
    			break;
    		case "3"://exit	
    			loggedin = false;
    			break;
    		default :
    			loggedin = false;
    			break;
    		}
        }
        
    }
	
	private boolean isLogout() {
		Session session = sessionManager.getSession(getSessionId());
		if (session == null) return true;
		else return false;
		
	}
}
