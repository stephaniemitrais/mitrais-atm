package com.mitrais.atm.controlller;

import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.service.AccountRepoImpl;
import com.mitrais.atm.service.AccountServiceImpl;
import com.mitrais.atm.service.TransferServiceImpl;
import com.mitrais.atm.service.WithdrawServiceImpl;
import com.mitrais.atm.view.HomeView;
import com.mitrais.atm.view.TransferView;
import com.mitrais.atm.view.WithdrawView;

public class HomeController extends BaseController {

	private HomeView view;
	
	public HomeController(SessionManager sessionManager, String sessionId, HomeView homeView) {
		super(sessionManager, sessionId);
		this.view = homeView;
	}

	public void displayMainMenu() {
              
        String menu;
        boolean loggedin = true;
        while(loggedin) {
            view.display();

            menu = view.getInput("Please choose option [3]:");

            switch (menu) {
    		case "1"://Withdraw
    			WithdrawController withdrawController = new WithdrawController(sessionManager, sessionId, new WithdrawView(), new WithdrawServiceImpl(new AccountServiceImpl(new AccountRepoImpl())), new AccountServiceImpl(new AccountRepoImpl()));
    			withdrawController.displayWithdraw();
    			if(isLogout()) loggedin = false;
    			break;
    		case "2"://Fund Transfer
    			TransferController transferController = new TransferController(sessionManager, sessionId, new TransferView(), new TransferServiceImpl(new AccountServiceImpl(new AccountRepoImpl())), new AccountServiceImpl(new AccountRepoImpl()));
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
