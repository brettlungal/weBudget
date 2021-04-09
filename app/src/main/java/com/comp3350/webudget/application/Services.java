package com.comp3350.webudget.application;

import com.comp3350.webudget.business.GroupLogic;
import com.comp3350.webudget.business.GroupWalletLogic;
import com.comp3350.webudget.business.IGroupLogic;
import com.comp3350.webudget.business.ITransactionLogic;
import com.comp3350.webudget.business.IUserLogic;
import com.comp3350.webudget.business.IUserWalletLogic;
import com.comp3350.webudget.business.IWalletLogic;
import com.comp3350.webudget.business.TransactionLogic;
import com.comp3350.webudget.business.UserLogic;
import com.comp3350.webudget.business.UserWalletLogic;

import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.ITransactionDatabase;
import com.comp3350.webudget.persistence.hsqldb.AccountDatabase;
import com.comp3350.webudget.persistence.hsqldb.TransactionDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestAccountDatabase;

import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.WalletDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestWalletDatabase;

import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.hsqldb.GroupDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestGroupDatabase;

import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.hsqldb.MembershipDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestMembershipDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;
    private static IWalletDatabase walletPersistence = null;
    private static IGroupDatabase groupPersistence = null;
    private static IMembershipDatabase membershipPersistence = null;
    private static ITransactionDatabase transactionDatabase = null;

    private static IWalletLogic userWalletLogic = null;
    private static IWalletLogic groupWalletLogic = null;
    private static IUserLogic userLogic = null;
    private static IGroupLogic groupLogic = null;
    private static ITransactionLogic transactionLogic = null;

    public static synchronized void testSetup(){
        groupPersistence = new TestGroupDatabase();
        membershipPersistence = new TestMembershipDatabase();
        accountPersistence = new TestAccountDatabase();
        walletPersistence = new TestWalletDatabase();

        userLogic = new UserLogic();
        userWalletLogic = new UserWalletLogic();
        groupWalletLogic = new GroupWalletLogic();
        groupLogic = new GroupLogic();
        transactionLogic = new TransactionLogic();
    }

    public static synchronized IAccountDatabase accountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountDatabase(Main.getDBPathName());
        }
        return accountPersistence;
    }

    public static synchronized IWalletDatabase walletPersistence(){
        if (walletPersistence == null) {
            walletPersistence = new WalletDatabase(Main.getDBPathName());
        }
        return walletPersistence;
    }

    public static synchronized IGroupDatabase groupPersistence(){
        if (groupPersistence == null){
            groupPersistence = new GroupDatabase(Main.getDBPathName());
        }
        return groupPersistence;
    }

    public static synchronized IMembershipDatabase membershipPersistence(){
        if(membershipPersistence == null){
            membershipPersistence = new MembershipDatabase(Main.getDBPathName());
        }
        return membershipPersistence;
    }

    public static synchronized ITransactionDatabase transactionPersistence(){
        if(transactionDatabase == null){
            transactionDatabase = new TransactionDatabase(Main.getDBPathName());
        }
        return transactionDatabase;
    }



    public static synchronized IUserLogic userLogic(){
        if(userLogic == null){
            userLogic = new UserLogic();
        }
        return userLogic;
    }

    public static synchronized IWalletLogic userWalletLogic(){
        if(userWalletLogic == null){
            userWalletLogic = new UserWalletLogic();
        }
        return userWalletLogic;
    }

    public static synchronized IWalletLogic groupWalletLogic(){
        if(groupWalletLogic == null){
            groupWalletLogic = new UserWalletLogic();
        }
        return groupWalletLogic;
    }

    public static synchronized IGroupLogic groupLogic(){
        if(groupLogic == null){
            groupLogic = new GroupLogic();
        }
        return groupLogic;
    }

    public static synchronized ITransactionLogic transactionLogic(){
        if ( transactionLogic == null ){
            transactionLogic = new TransactionLogic();
        }
        return transactionLogic;
    }


}
