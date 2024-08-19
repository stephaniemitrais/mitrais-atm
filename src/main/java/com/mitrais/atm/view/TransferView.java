package com.mitrais.atm.view;

public class TransferView extends ATMView {

	@Override
	public void display() {
		System.out.println("===============");
		System.out.println("Fund Transfer");
		System.out.println("===============");
		
	}

	public void displayTransferConfirm(String destinationAccount, Long amount, String referenceNo) {
		System.out.println("=====================");
		System.out.println("Transfer Confirmation");
		System.out.println("=====================");
		System.out.println("Destination Account: " + destinationAccount);
		System.out.println("Transfer Amount :" + amount );
		System.out.println("Reference Number :" + referenceNo);
		System.out.println();
		System.out.println("1. Confirm Trx");
		System.out.println("2. Cancel Trx");
	}
	
}
