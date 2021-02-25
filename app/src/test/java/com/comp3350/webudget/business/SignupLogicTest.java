package com.comp3350.webudget.business;


import org.junit.Before;
import org.junit.Test;

public class SignupLogicTest{

    @Before
    public void setUp() {

    }

    @Test(expected = SignupLogic.SignupException.class)
    public void signupFail() throws SignupLogic.SignupException{
        String[] input = {"xx","xx","user","password"};
        new SignupLogic(input);
        new SignupLogic(input);
    }


}