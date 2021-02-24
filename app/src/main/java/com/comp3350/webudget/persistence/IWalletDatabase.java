package com.comp3350.webudget.persistence;

public interface IWalletDatabase {
    public void insertWallet(int id,double balance,String ownerEmail);
    public void getWallet(int id);
}
