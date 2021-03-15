package com.comp3350.webudget.business;

import com.comp3350.webudget.application.SignupException;

import javax.security.auth.login.LoginException;

public interface IUserLogic {
    void signUp(String[] info) throws SignupException;
    void login(String[] info) throws LoginException;
    void logout();
    String getCurrentUser();
}
