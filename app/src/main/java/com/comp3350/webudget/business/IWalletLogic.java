package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.WalletException;

public interface IWalletLogic {
    public int getAmount(String id) throws Exception;
    public void deposit(String id, int amount) throws Exception;
    public void deposit( String id, String amount ) throws Exception;
    public void withdraw(String id, int amount) throws Exception;
}

