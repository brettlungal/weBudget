package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;

public class WalletPersistence {
    private static IWalletDatabase walletDB;

    public WalletPersistence(IWalletDatabase injectedWalletDB){
        walletDB = injectedWalletDB;
    }

    public WalletPersistence(){
        walletDB = new TestWalletDatabase();
    }

    public IWalletDatabase getDatabase(){
        return walletDB;
    }
}
