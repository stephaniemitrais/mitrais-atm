package com.mitrais.atm.account.entity;

import java.util.Objects;

public class Account{
	
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
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getBalance() {
		return balance;
	}

	public void deductBalance(Long amount){
		if(balance < amount) {
			throw new IllegalArgumentException("Insufficient balance: $" + balance);
		}
		
		this.balance = this.balance - amount;
	}

	public void addBalance(Long amount){
		this.balance = this.balance + amount;
	}

	public boolean validatePassword(String inputtedPassword) {
		if(this.password.equals(inputtedPassword)) return true;
		else return false;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNo, account.accountNo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }

}
