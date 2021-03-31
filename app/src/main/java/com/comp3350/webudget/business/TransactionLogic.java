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
import com.comp3350.webudget.persistence.ITransactionDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

public class TransactionLogic implements ITransactionLogic{

    private IWalletDatabase walletPersistence;
    private IAccountDatabase accountPersistence;
    private IGroupDatabase groupPersistence;
    private ITransactionDatabase transactionPersistence;
    private IUserLogic userLogic;

    public TransactionLogic(){
        walletPersistence = Services.walletPersistence();
        accountPersistence = Services.accountPersistence();
        groupPersistence = Services.groupPersistence();
        userLogic = Services.userLogic();
        //transactionPersistence = Services.transactionPersistence();
    }

    public TransactionLogic(final IWalletDatabase walletPersistence, final IAccountDatabase accountPersistence, final IGroupDatabase groupPersistence, final ITransactionDatabase transactionPersistence, IUserLogic userLogic){
        this.walletPersistence = walletPersistence;
        this.accountPersistence = accountPersistence;
        this.groupPersistence = groupPersistence;
        this.transactionPersistence = transactionPersistence;
        this.userLogic = userLogic;
    }

    @Override
    public void userToGroupTransaction( String username, String groupId, String amount ) throws WalletException, AccountException, GroupException {
        if ( amount.equals("") ){
            throw new WalletException("Transaction amount cannot be empty");
        }
        if ( groupId.equals("") ){
            throw new GroupException("Group name cannot be empty");
        }
        if ( !username.equals(userLogic.getCurrentUser()) ){
            throw new AccountException("Cannot transfer money from an account that isnt yours!");
        }
        Account user = accountPersistence.getAccount(username);
        Group group = groupPersistence.getGroup(Integer.parseInt(groupId));
        if ( user == null ){
            throw new AccountException("User account does not exist");
        }
        if ( group == null ){
            throw new GroupException("Group does not exist");
        }
        Wallet userWallet = walletPersistence.getWallet(user.getWalletID());
        Wallet groupWallet = walletPersistence.getWallet(group.getWallet());
        if ( userWallet == null || groupWallet == null ){
            throw new WalletException("Wallet does not exist");
        }
        //finally done error checking, can perform the transaction
        walletPersistence.transferMoney( userWallet.getWalletID(), groupWallet.getWalletID(), Integer.parseInt(amount) );

    }

    @Override
    public void groupToUserTransaction( String groupId, String username, String amount ) throws WalletException, AccountException, GroupException {
        if ( amount.equals("") ){
            throw new WalletException("Transaction amount cannot be empty");
        }
        if ( groupId.equals("") ){
            throw new GroupException("Group name cannot be empty");
        }
        Account user = accountPersistence.getAccount(username);
        Group group = groupPersistence.getGroup(Integer.parseInt(groupId));
        if ( user == null ){
            throw new AccountException("User account does not exist");
        }
        if ( group == null ){
            throw new GroupException("Group does not exist");
        }
        Wallet userWallet = walletPersistence.getWallet(user.getWalletID());
        Wallet groupWallet = walletPersistence.getWallet(group.getWallet());
        if ( userWallet == null || groupWallet == null ){
            throw new WalletException("Wallet does not exist");
        }
        //finally done error checking, can perform the transaction
        walletPersistence.transferMoney( groupWallet.getWalletID(), userWallet.getWalletID(), Integer.parseInt(amount) );
    }

    @Override
    public void userToUserTransaction(String sender, String receiver, String amount ) throws AccountException, WalletException {
        if ( !sender.equals(userLogic.getCurrentUser()) ){
            throw new AccountException("Cannot transfer money from an account that isnt yours!");
        }
        if ( amount.equals("") ){
            throw new WalletException("Transaction amount cannot be empty");
        }
        Account sendUser = accountPersistence.getAccount(sender);
        Account recvUser = accountPersistence.getAccount(receiver);
        if ( sendUser == null || recvUser == null ){
            throw new AccountException("User account does not exist");
        }
        Wallet senderWallet = walletPersistence.getWallet(sendUser.getWalletID());
        Wallet recverWallet = walletPersistence.getWallet(recvUser.getWalletID());
        if ( senderWallet == null || recverWallet == null ){
            throw new WalletException("Wallet does not exist");
        }
        //finally done error checking, can perform the transaction
        walletPersistence.transferMoney( senderWallet.getWalletID(), recverWallet.getWalletID(), Integer.parseInt(amount) );
    }
}
