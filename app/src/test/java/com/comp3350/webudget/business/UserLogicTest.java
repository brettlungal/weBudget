package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserLogicTest {
    //TODO flesh this out with tests

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IAccountDatabase testAccountDB;
    private IWalletDatabase testWalletDB;

    @Before
    public void setUp(){
        testWalletDB = new TestWalletDatabase();
        testAccountDB = new TestAccountDatabase(testWalletDB);
        Services.accountPersistence().insertUser("testF1", "testL1", "user1", "password1");
        Services.accountPersistence().insertUser("testF2", "testL2", "user2", "password2");
    }

    @Test(expected = SignupLogic.SignupException.class)
    public void signupFail() throws SignupLogic.SignupException {
        //String[] input = {"xx", "xx", "user", "password"};
        //new SignupLogic(input);
        //new SignupLogic(input);
    }
}
