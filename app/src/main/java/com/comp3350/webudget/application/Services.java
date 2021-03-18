package com.comp3350.webudget.application;

import com.comp3350.webudget.business.IUserLogic;
import com.comp3350.webudget.business.IUserWalletLogic;
import com.comp3350.webudget.business.UserLogic;
import com.comp3350.webudget.business.UserWalletLogic;
import com.comp3350.webudget.persistence.hsqldb.GroupDatabase;
import com.comp3350.webudget.persistence.IAccountDatabase;

import com.comp3350.webudget.persistence.hsqldb.AccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.TestAccountDatabase;
import com.comp3350.webudget.persistence.hsqldb.TestWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.WalletDatabase;

import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.TestGroupDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;
    private static IWalletDatabase walletPersistence = null;
    private static IGroupDatabase groupPersistence = null;
    private static IUserWalletLogic userWalletLogic = null;
    private static IUserLogic userLogic = null;


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

    public static synchronized IGroupDatabase groupPersistence(){
        if (groupPersistence == null){
            groupPersistence = new GroupDatabase();
        }
        return groupPersistence;
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
