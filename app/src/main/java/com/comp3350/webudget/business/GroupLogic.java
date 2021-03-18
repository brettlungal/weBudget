package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.util.ArrayList;

public class GroupLogic implements IGroupLogic {

    //TODO implement this class fully
    //Only exists so it can be called without error by the UI

    private IAccountDatabase accountPersistence;
    private IWalletDatabase walletPersistence;
    private IGroupDatabase groupPersistence;

    //default constructor
    public GroupLogic() {
        accountPersistence = Services.accountPersistence();
        walletPersistence = Services.walletPersistence();
        groupPersistence = Services.groupPersistence();
    }

    //injectable constructor
    public GroupLogic(final IAccountDatabase accountPersistence) {
        this.accountPersistence = accountPersistence;
    }

    @Override
    public Group getGroup(int groupID) throws GroupException {
        return null;
    }

    @Override
    public ArrayList<Group> getGroups() throws GroupException {
        return null;
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) throws AccountException, GroupException {
        return null;
    }

    @Override
    public int createEmptyGroup(String name) throws GroupException {
        return 0;
    }

    @Override
    public int createGroupWithUsers(String name, ArrayList<String> usernames) throws AccountException, GroupException {
        return 0;
    }

    @Override
    public void addUserToGroup(String username, int groupID) throws AccountException, GroupException {

    }

    @Override
    public void removeUserFromGroup(String username, int groupID) throws AccountException, GroupException {

    }
}
