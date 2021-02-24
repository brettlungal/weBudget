package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;

public class LoginLogic {

    private String username;
    private String password;

    public LoginLogic(String[] info){
        username = info[0];
        password = info[1];
    }

    public boolean isUserValid(){
        if(Services.accountPersistence().accountExist(username,password)){
            return true;
        }
        return false;
    }

}
