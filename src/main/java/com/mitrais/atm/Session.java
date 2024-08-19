package com.mitrais.atm;

public class Session {
	private final String sessionId;
    private final LoginUser loginUser;

    public Session(String sessionId, LoginUser user) {
        this.sessionId = sessionId;
        this.loginUser = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }
}
