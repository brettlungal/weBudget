package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;

public class SignupLogic {

    public SignupLogic(String[] info) throws SignupException{
        if (Services.accountPersistence().getAccount(info[0]) != null)
            throw new SignupException("User Name Has Been Taken !");

        Services.accountPersistence().insertUser(info[2], info[1], info[0], info[3]);;

    }

    public class SignupException extends Exception{
        public SignupException(String message){
            super(message);
        }
    }

}
