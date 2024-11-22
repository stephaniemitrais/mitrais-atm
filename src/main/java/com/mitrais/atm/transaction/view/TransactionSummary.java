package com.mitrais.atm.transaction.view;

public abstract class TransactionSummary {
	
	public void displaySummary(String message) {
		
        System.out.println(message);
        displaySpecificDetails();
    }
	
	protected abstract void displaySpecificDetails();
}
