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
    public void insertUser(Account account) {
        database.add(account);
    }
}
