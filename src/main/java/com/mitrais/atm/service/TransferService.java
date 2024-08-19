package com.mitrais.atm.service;

import com.mitrais.atm.model.Transfer;
import com.mitrais.atm.validation.TransferException;

public interface TransferService {

	Transfer createNewTransfer(String destinationAccNo, String sourceAccNo, Long amount) throws TransferException;

	void validateTransferAccount(String destinationAccNo, String sourceAccNo) throws TransferException;
	
	void validateTransferAmount(String sourceAccNo, Long amount) throws TransferException;
	
}
