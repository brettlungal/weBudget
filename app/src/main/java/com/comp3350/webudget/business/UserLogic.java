package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.application.SignupException;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import javax.security.auth.login.LoginException;

public class UserLogic implements IUserLogic {

    private IAccountDatabase accountPersistence;
    String currentUser = null;

    //default constructor
    public UserLogic() {
        accountPersistence = Services.accountPersistence();
    }

    //injectable constructor
    public UserLogic(final IAccountDatabase accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    @Override
    public void signUp(String[] info) throws SignupException {
        try {
            if (this.accountPersistence.getAccount(info[2]) != null) {
                throw new SignupException("User Name Has Been Taken !");
            }
        }catch(AccountException e){
            throw new SignupException("Error checking if username has been taken");
        }

        try {
            this.accountPersistence.insertUser(info[0], info[1], info[2], info[3]);
        }catch(AccountException e){
            throw new SignupException("Error adding user to signed-up users");
        }
    }

    @Override
    public void login(String[] info) throws LoginException {
        if(!this.accountPersistence.accountExist(info[0],info[1])){
            throw new LoginException("Invalid username or password");
        }
        this.currentUser = info[0];
    }

    @Override
    public void logout() {
        this.currentUser = null;
    }

    @Override
    public String getCurrentUser() {
        return this.currentUser;
    }
}
