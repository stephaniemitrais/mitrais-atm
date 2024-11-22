package com.mitrais.atm.account.service;

import com.mitrais.atm.LoginUser;

public interface LoginService {
	public boolean authenticate(String accountNo, String pin);
	
	public LoginUser getLoginUser(String accountNo, String pin);
}