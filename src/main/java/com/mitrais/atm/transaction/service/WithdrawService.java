package com.mitrais.atm.transaction.service;

import com.mitrais.atm.transaction.validation.WithdrawException;

public interface WithdrawService {
	public void withdraw(String accountNo, Long withdrawAmount) throws WithdrawException;
}
