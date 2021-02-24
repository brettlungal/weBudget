package com.comp3350.webudget.business;

import android.app.Service;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;

public class SignupLogic {
    Account account;

    public SignupLogic(String[] info){
        Services.accountPersistence().insertUser(new Account(info[2], info[1], info[0], info[3]));
    }

}
