package com.mitrais.atm.account.service;

import java.time.LocalDateTime;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.repo.AccountRepo;

public class LoginServiceImpl implements LoginService {
	private AccountRepo accountRepo;
	
	public LoginServiceImpl(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
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
