package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.AccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.WalletDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;
    private static IWalletDatabase walletPersistence = null;

    public static synchronized IAccountDatabase accountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountDatabase();
        }
        return accountPersistence;
    }

    public static synchronized IWalletDatabase walletPersistence(){
        if (walletPersistence == null) {
            walletPersistence = new WalletDatabase();
        }
        return walletPersistence;
    }


}
