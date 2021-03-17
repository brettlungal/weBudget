package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.objects.Account;

import java.util.ArrayList;

public interface IAccountDatabase {
    public boolean accountExist(String username, String password);
    public void insertUser(String fName, String lName, String username, String password)  throws AccountException;
    public Account getAccount(String username) throws AccountException;
    public ArrayList<Account> getAllAccounts() throws AccountException;
}
