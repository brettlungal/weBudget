package com.comp3350.webudget.application;

import com.comp3350.webudget.business.IUserWalletLogic;
import com.comp3350.webudget.business.UserWalletLogic;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.AccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;
import com.comp3350.webudget.persistence.WalletDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;
    private static IWalletDatabase walletPersistence = null;
    private static IUserWalletLogic userWalletLogic = null;

    public static synchronized void testSetup(){
        walletPersistence = new TestWalletDatabase();
        accountPersistence = new TestAccountDatabase(walletPersistence());
        userWalletLogic = new UserWalletLogic(accountPersistence, walletPersistence);
    }

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

    public static synchronized IUserWalletLogic userWalletLogic(){
        if(userWalletLogic == null){
            userWalletLogic = new UserWalletLogic();
        }
        return userWalletLogic;
    }


}
