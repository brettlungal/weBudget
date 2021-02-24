package com.comp3350.webudget;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.business.LoginLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Login_test {

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
    public void test_valid_login(){
        String[] info = {"user1","password1"};
        login = new LoginLogic(info);
        assertEquals(true, login.isUserValid());
    }

    @Test
    public void test_invalid_login(){ //neither username or password or ok
        String[] info = {"non_user","fake_password"};
        login = new LoginLogic(info);
        assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_invalid_username(){ //password is ok, but username isn't
        String[] info = {"non_user","password1"};
        login = new LoginLogic(info);
        assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_invalid_password(){ //username is ok, but has the wrong password
        String[] info = {"user1","wrong_password"};
        login = new LoginLogic(info);
        assertEquals(false, login.isUserValid());
    }

    @Test
    public void test_user_pass_mismatch(){ //both username and password are in the system, but belong to different accounts
        String[] info = {"user1","password2"};
        login = new LoginLogic(info);
        assertEquals(false, login.isUserValid());
    }
}
