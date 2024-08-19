package com.mitrais.atm.service;

import java.time.LocalDateTime;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.model.Account;

public class LoginServiceImpl implements LoginService {
	private AccountRepo accountRepo;
	
	public LoginServiceImpl() {
		this.accountRepo = new AccountRepoImpl();
	}
	
	@Override
	public boolean authenticate(String accountNo, String pin) {

	    Account account = accountRepo.getAccount(accountNo, pin);
	    
    	if (account == null) {
    		
    		return false;
    		
    	} else {
    		
    		return true;
    	}
	}

	@Override
	public LoginUser getLoginUser(String accountNo, String pin) {
		Account account = accountRepo.getAccount(accountNo, pin);
		
		LoginUser loginUser = new LoginUser(account);
		loginUser.setLastLoginTime(LocalDateTime.now());
		
		return loginUser;
	}
	
}
