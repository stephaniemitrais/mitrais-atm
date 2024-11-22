package com.mitrais.atm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mitrais.atm.LoginUser;
import com.mitrais.atm.account.entity.Account;
import com.mitrais.atm.account.repo.AccountRepo;
import com.mitrais.atm.account.service.LoginServiceImpl;

@RunWith(MockitoJUnitRunner.class) 
public class LoginServiceTest {

    @Mock
    private AccountRepo accountRepo;

    @InjectMocks
    private LoginServiceImpl loginService;

    private String accountNo;
    
    private String pin;
    
    @Before
    public void setup() {
    	accountNo = "112233";
    	pin = "123456";
    }
    @Test
    public void testAuthenticate_AccountFound() {

        Account expectedAccount = new Account(accountNo, "John Doe", pin, 1000L);
        // When
        when(accountRepo.getAccount(accountNo, pin)).thenReturn(expectedAccount);

        boolean authenticated = loginService.authenticate(accountNo, pin);

        // Then
        assertTrue(authenticated);
        verify(accountRepo).getAccount(accountNo, pin);
    }

    @Test
    public void testAuthenticate_AccountNotFound() {

        // When
        when(accountRepo.getAccount(accountNo, pin)).thenReturn(null);

        boolean authenticated = loginService.authenticate(accountNo, pin);

        // Then
        assertFalse(authenticated);
        verify(accountRepo).getAccount(accountNo, pin);
    }

    @Test
    public void testGetLoginUser_AccountFound() {

        Account expectedAccount = new Account(accountNo, "John Doe", pin, 1000L);
        
        // When
        when(accountRepo.getAccount(accountNo, pin)).thenReturn(expectedAccount);

        LoginUser loginUser = loginService.getLoginUser(accountNo, pin);

        // Then
        assertNotNull(loginUser);
        assertEquals(expectedAccount, loginUser.getAccount());

    }

    @Test
    public void testGetLoginUser_AccountNotFound() {
    	
        // When
        when(accountRepo.getAccount(accountNo, pin)).thenReturn(null);

        LoginUser loginUser = loginService.getLoginUser(accountNo, pin);

        // Then
        assertNull(loginUser.getAccount());
        verify(accountRepo).getAccount(accountNo, pin);
    }
}