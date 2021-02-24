package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

import java.util.ArrayList;

public class accountDatabase extends DatabaseAdapter{
    ArrayList<Account> database;

    public accountDatabase(){
        database = new ArrayList<>();
    }

    @Override
    public boolean accountExist(Account account){
        for(int i = 0; i < database.size(); i++){
            Account temp = database.get(i);
            if(temp.getUsername().equals(account.getUsername()) && temp.getPassword().equals(account.getPassword()))
                return true;
        }
        return false;
    }

    @Override
    public void insertUser(Account account) {
        database.add(account);
    }
}
