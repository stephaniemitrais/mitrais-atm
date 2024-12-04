package com.mitrais.atm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.repo.AccountRepoImpl;
import com.mitrais.atm.account.service.AccountServiceImpl;

@RunWith(MockitoJUnitRunner.class) 
public class AccountServiceTest {
	
	@Mock
    private AccountRepoImpl accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testGetAccountByAccountNo_AccountFound() {
        // Given
        String accountNo = "123456";
        Account expectedAccount = new Account("123456", "John Doe", "123456", 1000L);
        when(accountRepo.getAccount(accountNo)).thenReturn(expectedAccount);

        // When
        Account actualAccount = accountService.getAccountByAccountNo(accountNo);

        // Then
        assertEquals(expectedAccount, actualAccount);
    }
    
    
    @Test
    public void testGetAccountByAccountNoAndPin_AccountFound() {
        // Given
        String accountNo = "123456";
        String pin = "1234";
        
        Account expectedAccount = new Account("123456", "John Doe", "1234", 1000L);
        
        when(accountRepo.getAccount(accountNo, pin)).thenReturn(expectedAccount);

        // When
        Account actualAccount = accountService.getAccountByAccountNoAndPin(accountNo, pin);

        // Then
        assertEquals(expectedAccount, actualAccount);
    }
    
    
    
}
