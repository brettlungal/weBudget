package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.Exceptions.WalletException;
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
        Wallet wallet = walletPersistence.getWallet(walletID);
        return wallet.getBalance();
    }

    @Override
    public void deposit(String username, int amount) throws AccountException, WalletException {
        int walletID = getWalletID(username);
        if(amount <= 0 || amount > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 = $99,999");
        }
        walletPersistence.deposit(walletID, amount);
    }

    @Override
    public void deposit(String username, String amount) throws AccountException, WalletException {
        if ( amount.equals("") || amount.length()>5 || username.equals("")){
            throw new WalletException("Empty deposit amount received");
        }
        //if were here we have valid inputs
        int walletID = getWalletID(username);
        int amt = Integer.parseInt(amount);
        if(amt <= 0 || amt > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 - $99,999");
        }
        walletPersistence.deposit(walletID, amt);
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
}
