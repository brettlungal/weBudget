package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.application.SignupException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
//import com.comp3350.webudget.persistence.TestAccountDatabase;
//import com.comp3350.webudget.persistence.TestWalletDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

public class UserLogicTest {
    //TODO flesh this out with more tests

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IAccountDatabase testAccountDB;
    private IWalletDatabase testWalletDB;
    private IUserLogic testUserLogic;

    private final String[] user1Input = {"xx", "xx", "user1", "password1"};
    private final String[] user2Input = {"yy", "yy", "user2", "password2"};

    @Before
    public void setUp(){
        //testWalletDB = new TestWalletDatabase();
        //testAccountDB = new TestAccountDatabase(testWalletDB);
        testUserLogic = new UserLogic(testAccountDB);
    }

    //@After
    //public void cleanUp(){}
    //TODO need to remove the user from the database after running

    @Test
    public void signUpSuccess() throws SignupException{
        testUserLogic.signUp(user1Input);
    }

    @Test(expected = SignupException.class)
    public void signUpFail() throws SignupException {
        testUserLogic.signUp(user1Input);
        testUserLogic.signUp(user1Input);
    }

    @Test
    public void signUpMultiple() throws SignupException{
        testUserLogic.signUp(user1Input);
        testUserLogic.signUp(user2Input);
    }

    @Test(expected = LoginException.class)
    public void testNoUserLogin() throws LoginException{
        String[] info = {"user1","password1"};
        //exception.expect(LoginException.class);
        testUserLogic.login(info);
    }

    @Test
    public void test_valid_login() throws SignupException, LoginException {
        String[] info = {"user1","password1"};
        testUserLogic.signUp(user1Input);
        testUserLogic.login(info);
    }

    @Test(expected = LoginException.class)
    public void test_invalid_login() throws SignupException, LoginException { //neither username or password or ok
        String[] info = {"non_user","fake_password"};
        testUserLogic.signUp(user1Input);
        testUserLogic.login(info);
    }

    @Test(expected = LoginException.class)
    public void test_invalid_username() throws SignupException, LoginException { //password is ok, but username isn't
        String[] info = {"non_user","password1"};
        testUserLogic.signUp(user1Input);
        testUserLogic.login(info);
    }

    @Test(expected = LoginException.class)
    public void test_invalid_password() throws SignupException, LoginException { //username is ok, but has the wrong password
        String[] info = {"user1","wrong_password"};
        testUserLogic.signUp(user1Input);
        testUserLogic.login(info);
    }

    @Test(expected = LoginException.class)
    public void test_user_pass_mismatch() throws SignupException, LoginException { //both username and password are in the system, but belong to different accounts
        String[] info = {"user1","password2"};
        testUserLogic.signUp(user1Input);
        testUserLogic.login(info);
    }
}
