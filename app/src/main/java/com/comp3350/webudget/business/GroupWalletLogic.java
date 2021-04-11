package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.objects.Wallet;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

public class GroupWalletLogic implements IGroupWalletLogic {

    private IGroupDatabase groupPersistence;
    private IWalletDatabase walletPersistence;

    // default constructor
    public GroupWalletLogic() {
        groupPersistence = Services.groupPersistence();
        walletPersistence = Services.walletPersistence();
    }

    // injectable constructor
    public GroupWalletLogic(final IGroupDatabase groupPersistence, final IWalletDatabase walletPersistence) {
        this.groupPersistence = groupPersistence;
        this.walletPersistence = walletPersistence;
    }

    private int getWalletID(int id) throws GroupException {
        Group group = this.groupPersistence.getGroup(id); //TODO not the most efficient way... FIND A MORE EFFICIENT WAY TO DO THIS! (?)
        if(group == null){
            throw new GroupException("No Group with id " + id + " found.");
        }
        return group.getWallet();
    }

    public int getAmount(String id) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
        Wallet wallet = walletPersistence.getWallet(walletID);
        return wallet.getBalance();
    }

    public void deposit(String id, int amount) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
        if(amount <= 0 || amount > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 = $99,999");
        }
        walletPersistence.deposit(walletID, amount);
    }

    public void deposit(String id, String amount) throws GroupException, WalletException {
        if ( amount.equals("") || amount.length()>5 || id.equals("")){
            throw new WalletException("Empty deposit amount received");
        }
        //if were here we have valid inputs
        int walletID = getWalletID(Integer.parseInt(id));
        int amt = Integer.parseInt(amount);
        if(amt <= 0 || amt > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 - $99,999");
        }
        walletPersistence.deposit(walletID, amt);
    }

    public void withdraw(String id, int amount) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
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
