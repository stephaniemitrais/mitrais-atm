package com.mitrais.atm.service;

import com.mitrais.atm.validation.WithdrawException;

public interface WithdrawService {
	public void withdraw(String accountNo, Long withdrawAmount) throws WithdrawException;
}
