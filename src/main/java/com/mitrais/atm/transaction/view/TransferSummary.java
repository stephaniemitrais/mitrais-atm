package com.mitrais.atm.transaction.view;

public class TransferSummary extends TransactionSummary {

	private String destinationAccount;
	private Long transferedAmount;
	private String referenceNo;
	private Long balance;
	
	
	public TransferSummary(String destinationAccount, Long transferedAmount, String referenceNo, Long balance) {
		super();
		this.destinationAccount = destinationAccount;
		this.transferedAmount = transferedAmount;
		this.referenceNo = referenceNo;
		this.balance = balance;
	}


	@Override
	protected void displaySpecificDetails() {
		System.out.println("=====================");
		System.out.println("Fund Transfer Summary");
		System.out.println("=====================");
		System.out.println("Destination Account: " + destinationAccount);
		System.out.println("Transfer Amount :" + transferedAmount );
		System.out.println("Reference Number :" + referenceNo);
		System.out.println("Balance:" + "$" + balance);
		System.out.println();
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		
	}

}
