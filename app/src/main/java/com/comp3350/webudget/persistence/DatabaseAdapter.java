package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;

public class DatabaseAdapter implements DatabaseInterface{

    @Override
    public boolean accountExist(String username, String password){
        return false;
    }

    @Override
    public void insertUser(Account account) {

    }

    @Override
    public void insertWallet(int id, double balance, String ownerEmail) {

    }

    @Override
    public void getWallet(int id) {

    }

    @Override
    public void insertTransaction(int id, double amount) {

    }

    @Override
    public void getTransaction(int id) {

    }

    @Override
    public void insertGroup(int id) {

    }

    @Override
    public void getGroup(int id) {

    }
}
