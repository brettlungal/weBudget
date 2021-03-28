package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Transaction;
import com.comp3350.webudget.objects.Wallet;

import java.util.ArrayList;

public interface IUserWalletLogic {
    public int getAmount(String username) throws AccountException, WalletException;
    public void deposit(String username, int amount) throws AccountException, WalletException;
    public void deposit( String username, String amount ) throws AccountException, WalletException;
    public void withdraw(String username, int amount) throws AccountException, WalletException;
    public ArrayList<Transaction> getTransactions(String username) throws AccountException, WalletException;
}
