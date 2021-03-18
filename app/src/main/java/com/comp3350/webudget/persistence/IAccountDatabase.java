package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

public interface IAccountDatabase {
    public void insertUser(String fName, String lName, String username, String password);
    public Account getAccount(String username);

}
