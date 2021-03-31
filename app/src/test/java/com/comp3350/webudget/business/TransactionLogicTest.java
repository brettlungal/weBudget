package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.ITransactionDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestGroupDatabase;
import com.comp3350.webudget.persistence.TestMembershipDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;

import org.hsqldb.rights.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TransactionLogicTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IWalletDatabase testWalletDB;
    private IAccountDatabase testAccountDB;
    private IGroupDatabase testGroupDB;
    private IMembershipDatabase testMembershipDB;
    private ITransactionLogic testTransactionLogic;
    private ITransactionDatabase testTransactionDB;
    private IUserLogic userLogic;

    @Before
    public void setUp(){
        testWalletDB = new TestWalletDatabase();
        testAccountDB = new TestAccountDatabase(testWalletDB);
        testGroupDB = new TestGroupDatabase(testWalletDB);
        testMembershipDB = new TestMembershipDatabase(testAccountDB, testGroupDB);
        IUserLogic userLogic = new UserLogic(testAccountDB);
        testTransactionLogic = new TransactionLogic(testWalletDB,testAccountDB, testGroupDB, testTransactionDB ,userLogic);
    }
    //TODO fix issue with new override method introduced to walletdb interface
    @Test(expected = AccountException.class)
    public void getGroupSuccess() throws AccountException, GroupException, WalletException {
        testTransactionLogic.groupToUserTransaction("doesnt_exist","doesnt_exist","100");

    }



}
