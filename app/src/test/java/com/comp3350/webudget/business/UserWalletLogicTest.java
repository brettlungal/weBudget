package com.comp3350.webudget.business;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;
import com.comp3350.webudget.persistence.WalletDatabase;
import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.WalletException;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class UserWalletLogicTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IAccountDatabase testAccountDB;
    private IWalletDatabase testWalletDB;
    private UserWalletLogic userWalletLogic = null;

    @Before
    public void setUp(){
        testWalletDB = new TestWalletDatabase();
        testAccountDB = new TestAccountDatabase(testWalletDB);
        this.userWalletLogic = new UserWalletLogic(testAccountDB,testWalletDB);
    }

    //TODO test: get AccountError if the user searched for does not exist: get, deposit, withdraw, and get transactions

    @Test(expected = AccountException.class)
    public void testAccountDNEGet() throws AccountException, WalletException{
        try {
            this.userWalletLogic.getAmount("cloudself");
        }catch(AccountException e){
            throw AccountException("Account does not exist");
        }catch(WalletException e){
            throw WalletException("Wallet does not exist");
        }
    }

    //TODO test: balance = 0 on creation
    //TODO test: two created account's wallets have different IDs
    //TODO test: depositing -ve amounts invalid
    //TODO test: withdrawing -ve amounts invalid
    //TODO test: withdrawing more than is in the account invalid
    //TODO test: wallet balance increases after successful deposit
    //TODO test: wallet balance decreases after successful withdraw

    //TODO test: number of transactions on creation is 0
    //TODO test: number of transactions after deposits/withdraws matches the number of deposits/withdraws
    //TODO test: transaction after a single deposit has correct information
    //TODO test: transaction after a single withdraw has correct information

    //TODO: what else?
}
