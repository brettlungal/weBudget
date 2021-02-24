package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

public interface DatabaseInterface {

    public boolean accountExist(Account account);
    public void insertUser(Account account);
    public void insertWallet(int id,double balance,String ownerEmail);
    public void getWallet(int id);
    public void insertTransaction(int id,double amount);
    public void getTransaction(int id);
    public void insertGroup(int id);
    public void getGroup(int id);

}
