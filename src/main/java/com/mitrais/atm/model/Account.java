package com.mitrais.atm.model;

import java.util.Objects;

public class Account {
	
	private String name;
	private String accountNo;
	private String password;
	private Long balance;
	
    public Account(String name, String accountNo, String password, Long balance) {
        this.name = name;
    	this.accountNo = Objects.requireNonNull(accountNo);
        this.password = Objects.requireNonNull(password);
        this.balance = balance;
    }
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getBalance() {
		return balance;
	}
	
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
}
