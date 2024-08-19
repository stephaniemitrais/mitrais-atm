package com.mitrais.atm.view;

public class WithdrawalSummary extends TransactionSummary {

	private Long withdrawAmount;
	private Long balance;
	
	public WithdrawalSummary(Long withdrawAmount, Long balance) {
		super();
		this.withdrawAmount = withdrawAmount;
		this.balance = balance;
	}

	@Override
	protected void displaySpecificDetails() {
		System.out.println("Withdraw: " + "$" + withdrawAmount);
		System.out.println("Balance: " + "$" + balance);
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
				
	}

}
