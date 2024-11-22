package com.mitrais.atm.controlller;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.Session;
import com.mitrais.atm.SessionManager;
import com.mitrais.atm.account.entity.Account;

public abstract class BaseController {

	protected final SessionManager sessionManager;
	
	protected String sessionId;
	
	public BaseController(SessionManager sessionManager, String sessionId) {
		this.sessionManager = sessionManager;
		this.sessionId = sessionId;
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	protected String getLoginUserAccountNo() {
		
		Session session = sessionManager.getSession(getSessionId());
		
		LoginUser loginUser = session.getLoginUser();
		
		Account loginAccount = loginUser.getAccount();
		
		String accountNo = loginAccount.getAccountNo();
		
		return accountNo;
		
	}
}
