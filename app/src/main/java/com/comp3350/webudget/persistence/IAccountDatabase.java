package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

public interface IAccountDatabase {
    public boolean accountExist(String username, String password);
    public void insertUser(String fName, String lName, String username, String password);
    public Account getAccount(String username);

}
