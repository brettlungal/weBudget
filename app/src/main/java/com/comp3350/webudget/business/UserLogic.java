package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.application.SignupException;
import com.comp3350.webudget.objects.Account;
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
        if (Services.accountPersistence().getAccount(info[0]) != null)
            throw new SignupException("User Name Has Been Taken !");

        Services.accountPersistence().insertUser(info[2], info[1], info[0], info[3]);
    }

    @Override
    public void login(String[] info) throws LoginException {
        Account accountVerify = Services.accountPersistence().getAccount(info[0]);
        if(accountVerify == null){
            throw new LoginException("Invalid username or password");
        }
        else if(!accountVerify.getPassword().equals(info[1])){
            System.out.println(accountVerify.getPassword() + "    "+info[1] +"");
            throw new LoginException("Invalid username or password");
        }
        else{
            this.currentUser = info[0];
        }
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
