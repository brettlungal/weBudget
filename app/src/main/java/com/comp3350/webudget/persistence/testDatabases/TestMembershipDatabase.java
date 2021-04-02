package com.comp3350.webudget.persistence.testDatabases;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;

import java.util.ArrayList;

public class TestMembershipDatabase implements IMembershipDatabase {

    IAccountDatabase accountDatabase = null;
    IGroupDatabase groupDatabase = null;

    public TestMembershipDatabase(){
        accountDatabase = Services.accountPersistence();
        groupDatabase = Services.groupPersistence();
    }

    public TestMembershipDatabase(IAccountDatabase injectedWalletDatabase, IGroupDatabase injectedGroupDatabase){
        accountDatabase = injectedWalletDatabase;
        groupDatabase = injectedGroupDatabase;
    }

    public Boolean isUserInGroup(String username, int groupID) throws AccountException, GroupException {
        Group group = groupDatabase.getGroup(groupID);

        //test database only really needs to test one relationship. Real database would ideally only need to check that the single row is in the membershipDB
        return (group.getMemberIDs().indexOf(username) != -1);
    }

    @Override
    public void addUserToGroup(String username, int groupID)  throws AccountException, GroupException {
        Account user = accountDatabase.getAccount(username);
        Group group = groupDatabase.getGroup(groupID);
        //here, we can do it by a simple change of the object. In the real database, we need to access the Membership table.
        user.getGroupIDs().add(groupID);
        group.getMemberIDs().add(username);
    }

    @Override
    public void removeUserFromGroup(String username, int groupID)  throws AccountException, GroupException{
        Account user = accountDatabase.getAccount(username);
        Group group = groupDatabase.getGroup(groupID);
        //here, we can do it by a simple change of the object. In the real database, we need to access the Membership table.
        user.getGroupIDs().remove(new Integer(groupID));
        group.getMemberIDs().remove(username);
    }

    @Override
    public ArrayList<Group> getUserGroups(String username)  throws AccountException, GroupException{
        //horribly inefficient code, but it works for the test database.
        //In the actual database, this can be done in a single query, I think
        Account user = accountDatabase.getAccount(username);
        ArrayList<Group> userGroups = new ArrayList<>();

        for(int i = 0; i < user.getGroupIDs().size(); i++){
            userGroups.add(groupDatabase.getGroup(user.getGroupIDs().get(i)));
        }

        return userGroups;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID)  throws AccountException, GroupException{
        //horribly inefficient code, but it works for the test database.
        //In the actual database, this can be done in a single query, I think

        Group group = groupDatabase.getGroup(groupID);
        ArrayList<Account> groupUsers = new ArrayList<>();

        for(int i = 0; i < group.getMemberIDs().size(); i++){
            groupUsers.add(accountDatabase.getAccount(group.getMemberIDs().get(i)));
        }

        return groupUsers;
    }
}
