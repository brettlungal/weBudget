package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Transaction;
import com.comp3350.webudget.objects.Wallet;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.util.ArrayList;

public class UserWalletLogic implements IUserWalletLogic {

    private IAccountDatabase accountPersistence;
    private IWalletDatabase walletPersistence;
    //TODO should we store the current user/walletID to avoid checking the database too much?

    //default constructor
    public UserWalletLogic() {
        accountPersistence = Services.accountPersistence();
        walletPersistence = Services.walletPersistence();
    }

    //injectable constructor
    public UserWalletLogic(final IAccountDatabase accountPersistence, final IWalletDatabase walletPersistence) {
        this.accountPersistence = accountPersistence;
        this.walletPersistence = walletPersistence;
    }

    private int getWalletID(String username) throws AccountException{
        Account user = this.accountPersistence.getAccount(username); //TODO not the most efficient way... FIND A MORE EFFICIENT WAY TO DO THIS! (?)
        if(user == null){
            throw new AccountException("No Account with username " + username + " found.");
        }
        return user.getWalletID();
    }

    @Override
    public int getAmount(String username) throws AccountException, WalletException {
        int walletID = getWalletID(username);
        System.out.println(walletID);
        Wallet wallet = walletPersistence.getWallet(walletID);
        return wallet.getBalance();
    }

    @Override
    public void deposit(String username, int amount) throws AccountException, WalletException {
        int walletID = getWalletID(username);
        System.out.println(walletID);
        if(amount <= 0){
            throw new WalletException("Deposit to wallet must be positive");
        }
        walletPersistence.deposit(walletID, amount);
    }

    @Override
    public void withdraw(String username, int amount) throws AccountException, WalletException {
        int walletID = getWalletID(username);
        if(amount <= 0){
            throw new WalletException("Withdraw from wallet must be positive");
        }
        Wallet wallet = walletPersistence.getWallet(walletID);
        if(amount > wallet.getBalance()){
            throw new WalletException("Cannot withdraw more money than is in the wallet");
        }
        walletPersistence.withdraw(walletID, amount);
    }

    @Override
    public ArrayList<Transaction> getTransactions(String username) throws AccountException, WalletException {
        return null;
    }
}
