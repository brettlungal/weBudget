package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Wallet;
import com.comp3350.webudget.persistence.IWalletDatabase;

public class WalletLogic implements IWalletLogic {

    private IWalletDatabase walletPersistence;

    //default constructor
    public WalletLogic() {
        walletPersistence = Services.walletPersistence();
    }

    //injectable constructor
    public WalletLogic(final IWalletDatabase walletPersistence) {
        this.walletPersistence = walletPersistence;
    }

    public int getAmount(String id) throws WalletException {
        Wallet wallet = walletPersistence.getWallet(Integer.parseInt(id));
        return wallet.getBalance();
    }

    public void deposit(String id, int amount) throws WalletException {
        if(amount <= 0 || amount > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 = $99,999");
        }
        walletPersistence.deposit(Integer.parseInt(id), amount);
    }

    public void deposit( String id, String amount ) throws WalletException {
        if ( amount.equals("") || amount.length()>5 || id.equals("")){
            throw new WalletException("Empty deposit amount received");
        }
        //if were here we have valid inputs
        int amt = Integer.parseInt(amount);
        if(amt <= 0 || amt > 99999){
            throw new WalletException("Deposit to wallet must be within range $0 - $99,999");
        }
        walletPersistence.deposit(Integer.parseInt(id), amt);
    }

    public void withdraw(String id, int amount) throws WalletException {
        if(amount <= 0){
            throw new WalletException("Withdraw from wallet must be positive");
        }
        Wallet wallet = walletPersistence.getWallet(Integer.parseInt(id));
        if(amount > wallet.getBalance()){
            throw new WalletException("Cannot withdraw more money than is in the wallet");
        }
        walletPersistence.withdraw(Integer.parseInt(id), amount);
    }
}
