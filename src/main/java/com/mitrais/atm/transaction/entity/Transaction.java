package com.mitrais.atm.transaction.entity;

import java.time.LocalDateTime;

import com.mitrais.atm.account.entity.Account;

public class Transaction {
	private LocalDateTime transactionDatetime;
	private Account account;
	private Long amount;
	
	private TransactionType transactionType;
	
	public LocalDateTime getTransactionDatetime() {
		return transactionDatetime;
	}
	public void setTransactionDatetime(LocalDateTime transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
}
