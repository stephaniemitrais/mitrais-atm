package com.mitrais.atm.controlller;

import com.mitrais.atm.SessionManager;

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
	
	
}
