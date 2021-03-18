package com.comp3350.webudget.application;

import com.comp3350.webudget.business.IUserLogic;
import com.comp3350.webudget.business.IUserWalletLogic;
import com.comp3350.webudget.business.UserLogic;
import com.comp3350.webudget.business.UserWalletLogic;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.hsqldb.AccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.TestAccountDatabase;
import com.comp3350.webudget.persistence.hsqldb.TestWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.WalletDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;
    private static IWalletDatabase walletPersistence = null;
    private static IUserWalletLogic userWalletLogic = null;
    private static IUserLogic userLogic = null;

    public static synchronized void testSetup(){
        walletPersistence = new TestWalletDatabase();
        accountPersistence = new TestAccountDatabase();
        userLogic = new UserLogic();
        userWalletLogic = new UserWalletLogic();
    }

    public static synchronized IAccountDatabase accountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountDatabase(Main.getDBPathName());
        }
        return accountPersistence;
    }

    public static synchronized IWalletDatabase walletPersistence(){
        if (walletPersistence == null) {
            walletPersistence = new WalletDatabase();
        }
        return walletPersistence;
    }

    public static synchronized IUserLogic userLogic(){
        if(userLogic == null){
            userLogic = new UserLogic();
        }
        return userLogic;
    }

    public static synchronized IUserWalletLogic userWalletLogic(){
        if(userWalletLogic == null){
            userWalletLogic = new UserWalletLogic();
        }
        return userWalletLogic;
    }


}
