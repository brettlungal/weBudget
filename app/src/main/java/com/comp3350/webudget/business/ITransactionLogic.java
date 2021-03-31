package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.Exceptions.WalletException;

public interface ITransactionLogic {

    void userToGroupTransaction( String username, String group, String amount ) throws WalletException, AccountException, GroupException;
    void groupToUserTransaction( String group, String username, String amount ) throws WalletException, AccountException, GroupException;
    void userToUserTransaction( String sender, String receiver, String amount ) throws WalletException, AccountException;

}
