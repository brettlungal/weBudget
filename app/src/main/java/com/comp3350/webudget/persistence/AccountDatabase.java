package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

import java.util.ArrayList;

public class AccountDatabase implements IAccountDatabase {
    ArrayList<Account> database;

    public AccountDatabase(){
        database = new ArrayList<>();
    }

    @Override
    public boolean accountExist(String username, String password){
        for(int i = 0; i < database.size(); i++){
            Account temp = database.get(i);
            if(temp.getUsername().equals(username) && temp.getPassword().equals(password))
                return true;
        }
        return false;
    }

    @Override
    public void insertUser(String fName, String lName, String username, String password){
        database.add(new Account(fName, lName, username, password));
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
