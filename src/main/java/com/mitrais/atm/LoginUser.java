package com.mitrais.atm;

import java.time.LocalDateTime;

import com.mitrais.atm.account.entity.Account;

public class LoginUser {

	private Account account;
	
	private LocalDateTime lastLoginTime;
	
	public LoginUser(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
}
