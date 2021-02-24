package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;

public class LoginLogic {

    private Account account;

    public LoginLogic(String[] info){
        account = new Account("chaos","monkey",99,info[0],info[1]);
    }

    public boolean isUserValid(){
        if(Services.accountPersistence().accountExist(account)){
            return true;
        }
        return false;
    }

}
