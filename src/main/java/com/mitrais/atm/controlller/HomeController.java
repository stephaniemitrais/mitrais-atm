package com.mitrais.atm.controlller;

import java.io.IOException;

import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.account.repo.AccountRepoImpl;
import com.mitrais.atm.account.service.AccountServiceImpl;
import com.mitrais.atm.transaction.controller.TransactionController;
import com.mitrais.atm.transaction.controller.TransferController;
import com.mitrais.atm.transaction.controller.WithdrawController;
import com.mitrais.atm.transaction.repo.TransactionRepoImpl;
import com.mitrais.atm.transaction.service.TransactionServiceImpl;
import com.mitrais.atm.transaction.service.TransferServiceImpl;
import com.mitrais.atm.transaction.service.WithdrawServiceImpl;
import com.mitrais.atm.transaction.view.TransactionHistoryView;
import com.mitrais.atm.transaction.view.TransferView;
import com.mitrais.atm.transaction.view.WithdrawView;
import com.mitrais.atm.view.HomeView;

public class HomeController extends BaseController {

	private HomeView view;
	
	public HomeController(SessionManager sessionManager, String sessionId, HomeView homeView) {
		super(sessionManager, sessionId);
		this.view = homeView;
	}

	public void displayMainMenu() throws IOException {
              
        String menu;
        boolean loggedin = true;
        while(loggedin) {
            view.display();

            menu = view.getInput("Please choose option [3]:");

            switch (menu) {
    		case "1"://Withdraw
    			WithdrawController withdrawController = new WithdrawController(sessionManager, sessionId, new WithdrawView(), new WithdrawServiceImpl(new AccountServiceImpl(new AccountRepoImpl()), new TransactionRepoImpl()), new AccountServiceImpl(new AccountRepoImpl()));
    			withdrawController.displayWithdraw();
    			if(isLogout()) loggedin = false;
    			break;
    		case "2"://Fund Transfer
    			TransferController transferController = new TransferController(sessionManager, sessionId, new TransferView(), new TransferServiceImpl(new AccountServiceImpl(new AccountRepoImpl()), new TransactionRepoImpl()), new AccountServiceImpl(new AccountRepoImpl()));
    			transferController.displayTransfer();
    			if(isLogout()) loggedin = false;
    			break;
    		case "3"://Transaction History
    			TransactionController transactionController = new TransactionController(sessionManager, sessionId, new TransactionServiceImpl(new TransactionRepoImpl()), new TransactionHistoryView());
    			transactionController.displayTransactionHistory();
    			if(isLogout()) loggedin = false;
    			break;
    		case "4"://exit	
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
