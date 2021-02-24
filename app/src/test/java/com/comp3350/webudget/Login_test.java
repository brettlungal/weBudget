package com.comp3350.webudget;

import com.comp3350.webudget.application.InvalidLoginException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.business.LoginLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class Login_test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Account acct;
    private LoginLogic login;
    @Before
    public void setUp(){
        Services.accountPersistence().insertUser("testF1", "testL1", "user1", "password1");
        Services.accountPersistence().insertUser("testF2", "testL2", "user2", "password2");
    }

    //@After
    //public void cleanUp(){}
    //TODO need to remove the user from the database after running

    @Test
    public void test_valid_login() throws InvalidLoginException {
        String[] info = {"user1","password1"};
        login = new LoginLogic(info);
        //assertEquals(true, login.isUserValid());
    }

    @Test
    public void test_invalid_login() throws InvalidLoginException { //neither username or password or ok
        String[] info = {"non_user","fake_password"};
        exception.expect(InvalidLoginException.class);
        login = new LoginLogic(info);
        //assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_invalid_username() throws InvalidLoginException { //password is ok, but username isn't
        String[] info = {"non_user","password1"};
        exception.expect(InvalidLoginException.class);
        login = new LoginLogic(info);
        //assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_invalid_password() throws InvalidLoginException { //username is ok, but has the wrong password
        String[] info = {"user1","wrong_password"};
        exception.expect(InvalidLoginException.class);
        login = new LoginLogic(info);
        //assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_user_pass_mismatch() throws InvalidLoginException { //both username and password are in the system, but belong to different accounts
        String[] info = {"user1","password2"};
        exception.expect(InvalidLoginException.class);
        login = new LoginLogic(info);
        //assertEquals(false, login.isUserValid());
    }
}
