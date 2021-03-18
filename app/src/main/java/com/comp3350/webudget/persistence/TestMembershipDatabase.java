package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public class TestMembershipDatabase implements IMembershipDatabase{

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

    @Override
    public void addUserToGroup(String username, int groupID)  throws AccountException, GroupException {
        Account user = accountDatabase.getAccount(username);
        if ( user == null ){
            throw new AccountException("Account doesnt exist");
        }
        Group group = groupDatabase.getGroup(groupID);
        if ( group == null ){
            throw new GroupException("Group doesnt exist");
        }
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
        if ( user == null ){
            throw new AccountException("User does not exist");
        }
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
