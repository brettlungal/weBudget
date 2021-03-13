package com.comp3350.webudget.persistence;
import com.comp3350.webudget.objects.Wallet;


import com.comp3350.webudget.objects.Wallet;

public interface IWalletDatabase {
    public int insertWallet(String ownerName); //returns the ID of the wallet
    public Wallet getWallet(int id);
}
