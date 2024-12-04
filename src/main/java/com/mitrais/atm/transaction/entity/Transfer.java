package com.mitrais.atm.transaction.entity;

import com.mitrais.atm.account.entity.Account;

public class Transfer extends Transaction {

	private Account toAccount;

	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	
}
