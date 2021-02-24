package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.application.InvalidLoginException;

public class LoginLogic {

    public LoginLogic(String[] info) throws InvalidLoginException {
        if(!Services.accountPersistence().accountExist(info[0],info[1])){
            throw new InvalidLoginException("Invalid username or password");
        }
    }

}
