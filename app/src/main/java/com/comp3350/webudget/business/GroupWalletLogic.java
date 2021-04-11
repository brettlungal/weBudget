package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

public class GroupWalletLogic implements IGroupWalletLogic {

    private IGroupDatabase groupPersistence;
    private IWalletLogic walletLogic;


    // default constructor
    public GroupWalletLogic() {
        groupPersistence = Services.groupPersistence();
        walletLogic = Services.walletLogic();
    }

    // injectable constructor
    public GroupWalletLogic(final IGroupDatabase groupPersistence, final IWalletLogic walletLogic) {
        this.groupPersistence = groupPersistence;
        this.walletLogic = walletLogic;
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
        return walletLogic.getAmount(walletID);
    }

    public void deposit(String id, int amount) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
        walletLogic.deposit(walletID, amount);
    }

    public void deposit(String id, String amount) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
        walletLogic.deposit(walletID, Integer.parseInt(amount));
    }

    public void withdraw(String id, int amount) throws GroupException, WalletException {
        int walletID = getWalletID(Integer.parseInt(id));
        walletLogic.withdraw(walletID, amount);
    }
}
