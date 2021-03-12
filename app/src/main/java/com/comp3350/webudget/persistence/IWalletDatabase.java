package com.comp3350.webudget.persistence;
import com.comp3350.webudget.objects.Wallet;


public interface IWalletDatabase {
    public void insertWallet(int id,double balance,String ownerEmail);
    public Wallet getWallet(int id);
}
