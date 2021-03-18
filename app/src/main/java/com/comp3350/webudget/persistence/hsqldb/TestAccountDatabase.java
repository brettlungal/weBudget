package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.util.ArrayList;

public class TestAccountDatabase implements IAccountDatabase {
    ArrayList<Account> database;
    IWalletDatabase walletDatabase = null;

    public TestAccountDatabase(){
        database = new ArrayList<>();
        walletDatabase = Services.walletPersistence();
    }

    public TestAccountDatabase(IWalletDatabase injectedWalletDatabase){
        database = new ArrayList<>();
        walletDatabase = injectedWalletDatabase;
    }

//    @Override
//    public boolean accountExist(String username, String password){
//        for(int i = 0; i < database.size(); i++){
//            Account temp = database.get(i);
//            if(temp.getUsername().equals(username) && temp.getPassword().equals(password))
//                return true;
//        }
//        return false;
//    }

    @Override
    public void insertUser(String fName, String lName, String username, String password){
        int walletID = walletDatabase.insertWallet(username);
        database.add(new Account(fName, lName, username, password, walletID, null));
    }

    @Override
    public Account getAccount(String username) {
        for(int i = 0; i < database.size(); i++){
            Account temp = database.get(i);
            if(temp.getUsername().equals(username))
                return temp;
        }
        return null;
    }
}
