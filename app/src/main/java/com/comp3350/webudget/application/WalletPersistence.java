package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.AccountDatabase;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;

public class WalletPersistence {
    private static IWalletDatabase walletPersistence = null;

    //dependancy injected singleton, where you only have to pass the class you want instantiated
    //This is either genius, or a HORRIBLE hack
    public static synchronized <IWD extends IWalletDatabase> IWalletDatabase getPersistence(Class<IWD> injectedWalletDB){
        if (walletPersistence == null) {
            try {
                walletPersistence = injectedWalletDB.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return walletPersistence;
    }

    public IWalletDatabase getPersistence(){
        return getPersistence(TestWalletDatabase.class);
    }

}
