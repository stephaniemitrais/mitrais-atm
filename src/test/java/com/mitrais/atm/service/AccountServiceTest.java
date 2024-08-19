package com.mitrais.atm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mitrais.atm.model.Account;

@RunWith(MockitoJUnitRunner.class) 
public class AccountServiceTest {
	@Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService = new AccountServiceImpl();

    @Test
    public void testGetAccountBalance_AccountBalanceFound() {
        // Given
        String accountNo = "123456";
        Long expectedBalance = 1000L;
        Account account = new Account(accountNo, "John Doe", "123456", expectedBalance);
        when(accountRepo.getAccount(accountNo)).thenReturn(account);

        // When
        Long actualBalance = accountService.getAccountBalance(accountNo);

        // Then
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testSetAccountBalance_AccountBalanceUpdated() {
        // Given
        String accountNo = "123456";
        Long newBalance = 2000L;
        Account account = new Account(accountNo, "John Doe", "123456", 1000L);
        when(accountRepo.getAccount(accountNo)).thenReturn(account);

        // When
        accountService.setAccountBalance(accountNo, newBalance);

        // Then
        verify(accountRepo).updateAccount(account);
        assertEquals(newBalance, account.getBalance());
    }

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
