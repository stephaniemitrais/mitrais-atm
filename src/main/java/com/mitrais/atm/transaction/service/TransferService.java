package com.mitrais.atm.transaction.service;

import com.mitrais.atm.transaction.entity.Transfer;
import com.mitrais.atm.transaction.validation.TransferException;

public interface TransferService {

	Transfer createNewTransfer(String destinationAccNo, String sourceAccNo, Long amount) throws TransferException;

	void validateTransferAccount(String destinationAccNo, String sourceAccNo) throws TransferException;
	
	void validateTransferAmount(String sourceAccNo, Long amount) throws TransferException;
	
}
