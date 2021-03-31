package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

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
        Group group = groupPersistence.getGroup(groupID);
        if(group == null){
            throw new GroupException("Group with ID " + groupID + " could not be found");
        }
        return groupPersistence.getGroup(groupID);
    }

    @Override
    public ArrayList<Group> getGroups() throws GroupException {
        ArrayList<Group> groups = groupPersistence.getAllGroups();
        if(groups == null){
            throw new GroupException("No list of groups found");
        }
        return groups;
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) throws AccountException, GroupException {
        if(username == null){
            throw new AccountException("Username invalid. Please enter a valid username");
        }

        Account user = accountPersistence.getAccount(username);

        if(user == null){
            throw new AccountException("User not found");
        }

        ArrayList<Group> userGroups = membershipPersistence.getUserGroups(username);

        if(userGroups == null){
            throw new GroupException("No list of groups found");
        }

        return userGroups;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID)  throws AccountException, GroupException {
        Group group = groupPersistence.getGroup(groupID);

        if(group == null){
            throw new GroupException("User not found");
        }

        ArrayList<Account> groupUsers = membershipPersistence.getGroupUsers(groupID);

        if(groupUsers == null){
            throw new GroupException("No list of groups found");
        }

        return membershipPersistence.getGroupUsers(groupID);
    }

    @Override
    public int createEmptyGroup(String name) throws GroupException {
        if(name == null){
            throw new GroupException("Group name invalid. Please enter a valid group name");
        }
        return groupPersistence.insertGroup(name, new ArrayList<String>());
    }

    private void verifyUsernames(ArrayList<String> usernames) throws AccountException{
        //iteratively check that every username corresponds to a real user
        for(String username : usernames){
            if(username == null){
                throw new AccountException("One of the usernames entered was invalid. Please enter valid usernames only");
            }
            Account user = accountPersistence.getAccount(username);
            if(user == null){
                throw new AccountException("User \"" + username + "\" not found");
            }
        }
    }

    /*
    Returns a new ArrayList that doesn't contain any duplicates
     */
    private ArrayList<String> removeDuplicates(ArrayList<String> usernames){
        //to remove duplicates, convert to and from a Set, which is designed to efficiently avoid duplicate items
        ArrayList<String> uniqueNamesArray;
        LinkedHashSet<String> uniqueNamesSet = new LinkedHashSet<String>(usernames);
        uniqueNamesArray = new ArrayList<String>(uniqueNamesSet);

        return uniqueNamesArray;
    }

    @Override
    public int createGroupWithUsers(String name, ArrayList<String> usernames) throws AccountException, GroupException {
        if(name == null){
            throw new GroupException("Group name invalid. Please enter a valid group name");
        }

        //Remove any duplicates from the list of usernames. Duplicate usernames can cause integrity errors, so its important to remove them.
        ArrayList<String> uniqueNames = removeDuplicates(usernames);
        //Check if all usernames are associated with valid accounts
        verifyUsernames(uniqueNames);

        return groupPersistence.insertGroup(name, uniqueNames);
    }

    @Override
    public void addUserToGroup(String username, int groupID) throws AccountException, GroupException {
        //check that both the user and group exist
        if(username == null){
            throw new AccountException("One of the usernames entered was invalid. Please enter valid usernames only");
        }

        Account user = accountPersistence.getAccount(username);
        if(user == null){
            throw new AccountException("User \"" + username + "\" not found");
        }

        Group group = groupPersistence.getGroup(groupID);

        if(group == null){
            throw new GroupException("User not found");
        }

        //check that the user is not already a member of the group

        if(!membershipPersistence.isUserInGroup(username, groupID)){
            membershipPersistence.addUserToGroup(username, groupID);
        }else{
            throw new MembershipException
        }


    }

    @Override
    public void removeUserFromGroup(String username, int groupID) throws AccountException, GroupException {
        //check that both the user and group exist
        if(username == null){
            throw new AccountException("One of the usernames entered was invalid. Please enter valid usernames only");
        }

        Account user = accountPersistence.getAccount(username);
        if(user == null){
            throw new AccountException("User \"" + username + "\" not found");
        }

        Group group = groupPersistence.getGroup(groupID);

        if(group == null){
            throw new GroupException("User not found");
        }

        //TODO check that the user is indeed already a member of the group
        membershipPersistence.addUserToGroup(username, groupID);
    }
}
