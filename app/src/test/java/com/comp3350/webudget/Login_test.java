package com.comp3350.webudget;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.business.LoginLogic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Login_test {

    private Account acct;
    private LoginLogic login;
    @Before
    public void setUp(){
        acct = new Account("testF","testL","user","password");
    }

    @Test
    public void test_invalid_login(){
        login = new login()
    }
}
