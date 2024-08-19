package com.mitrais.atm.view;

public class HomeView extends ATMView {

	@Override
	public void display() {
		System.out.println("================");
	    System.out.println("Transaction Menu");
	    System.out.println("================");
		
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		
	}

}
