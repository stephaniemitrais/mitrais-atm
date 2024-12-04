package com.mitrais.atm.transaction.view;

import com.mitrais.atm.view.ATMView;

public class WithdrawView extends ATMView {

	@Override
	public void display() {
		System.out.println("=========");
	    System.out.println("Withdraw");
	    System.out.println("=========");
		System.out.println("1. $10");
		System.out.println("2. $50");
		System.out.println("3. $100");
		System.out.println("4. Other");
		System.out.println("5. Back");
		
	}

}
