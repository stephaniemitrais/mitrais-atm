package com.mitrais.atm.view;

public class LoginView extends ATMView {

	public String accountNo;
	public String pin;
		
	public String getAccountNo() {
        return getInput("Enter account no:");
    }

    public String getPin() {
        return getInput("Enter pin:");
    }

	@Override
	public void display() {
		System.out.println("===========================");
	    System.out.println("Welcome to Mitrais Bank ATM");
	    System.out.println("===========================");
		
	}
	
}
