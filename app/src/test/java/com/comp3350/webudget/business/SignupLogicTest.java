package com.comp3350.webudget.business;


import com.comp3350.webudget.application.AccountException;

import org.junit.Before;
import org.junit.Test;

public class SignupLogicTest {

    @Before
    public void setUp() {

    }

    @Test(expected = SignupLogic.SignupException.class)
    public void signupFail() throws SignupLogic.SignupException {
        String[] input = {"xx", "xx", "user", "password"};
        new SignupLogic(input);
        new SignupLogic(input);
    }

    /*
    //test: two created account's wallets have different IDs
    @Test
    public void testSeparateWallets throws AccountException, WalletException {
        this.testAccountDB.insertUser("u1","u1","user1","admin");
        this.testAccountDB.insertUser("u2","u2","user2","admin");
    }
    */
}