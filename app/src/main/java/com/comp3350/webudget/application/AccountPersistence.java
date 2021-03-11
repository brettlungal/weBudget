package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;

public class AccountPersistence {
    private static IAccountDatabase accountDB;

    public AccountPersistence(IAccountDatabase injectedAccountDB){
        accountDB = injectedAccountDB;
    }

    public AccountPersistence(){
        accountDB = new TestAccountDatabase();
    }

    public static IAccountDatabase getDatabase(){
        return accountDB;
    }
}
