package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;

public class LoginLogic {

    public LoginLogic(String[] info) throws InvalidLoginException {

        if(Services.accountPersistence().getAccount(info[0]) == null){
            throw new InvalidLoginException("Invalid username or password");
        }
    }

    public class InvalidLoginException extends Exception{

        public InvalidLoginException(){
            super();
        }

        public InvalidLoginException(String message){
            super(message);
        }
    }

}
