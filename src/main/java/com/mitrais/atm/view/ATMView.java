package com.mitrais.atm.view;

import java.util.Scanner;

public abstract class ATMView {
	
	private Scanner scanner = new Scanner(System.in);
	
    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();

    }
    
	public abstract void display();
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
}
