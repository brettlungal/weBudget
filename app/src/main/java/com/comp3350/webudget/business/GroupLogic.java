package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;

import java.util.ArrayList;

public class GroupLogic implements IGroupLogic {

    //TODO implement this class fully
    //Only exists so it can be called without error by the UI

    private IAccountDatabase accountPersistence;
    private IGroupDatabase groupPersistence;
    private IMembershipDatabase membershipPersistence;

    //default constructor
    public GroupLogic() {
        accountPersistence = Services.accountPersistence();
        groupPersistence = Services.groupPersistence();
        membershipPersistence = Services.membershipPersistence();
    }

    //injectable constructor
    public GroupLogic(final IAccountDatabase accountPersistence, final IGroupDatabase groupPersistence, final IMembershipDatabase membershipPersistence) {
        this.accountPersistence = accountPersistence;
        this.groupPersistence = groupPersistence;
        this.membershipPersistence = membershipPersistence;
    }

    @Override
    public Group getGroup(int groupID) throws GroupException {
        return groupPersistence.getGroup(groupID);
    }

    @Override
    public ArrayList<Group> getGroups() throws GroupException {
        return groupPersistence.getAllGroups();
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) throws AccountException, GroupException {
        return membershipPersistence.getUserGroups(username);
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID)  throws AccountException, GroupException {
        return membershipPersistence.getGroupUsers(groupID);
    }

    @Override
    public int createEmptyGroup(String name) throws GroupException {
        return groupPersistence.insertGroup(name, new ArrayList<String>());
    }

    @Override
    public int createGroupWithUsers(String name, ArrayList<String> usernames) throws AccountException, GroupException {
        //TODO check if all usernames have accounts, and there are no duplicate usernames
        return groupPersistence.insertGroup(name, usernames);
    }

    @Override
    public void addUserToGroup(String username, int groupID) throws AccountException, GroupException {
        //TODO check that both the user and group exist
        //TODO check that the user is not already a member of the group
        membershipPersistence.addUserToGroup(username, groupID);
    }

    @Override
    public void removeUserFromGroup(String username, int groupID) throws AccountException, GroupException {
        //TODO check that both the user and group exist
        //TODO check that the user is indeed already a member of the group
        membershipPersistence.addUserToGroup(username, groupID);
    }
}
