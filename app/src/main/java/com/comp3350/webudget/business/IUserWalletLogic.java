package com.comp3350.webudget.business;

import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Transaction;

import java.util.ArrayList;

public interface IUserWalletLogic {
    public int getAmount(String username);
    public void deposit(String username, int amount) throws WalletException;
    public void withdraw(String username, int amount) throws WalletException;
    public ArrayList<Transaction> getTransactions(String username);
}
