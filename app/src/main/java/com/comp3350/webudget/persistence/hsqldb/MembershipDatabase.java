package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IMembershipDatabase;

import java.util.ArrayList;

public class MembershipDatabase implements IMembershipDatabase {
    @Override
    public void addUserToGroup(String username, int groupID) {
        //TODO implement when the feature gets implemented
    }

    @Override
    public void removeUserFromGroup(String username, int groupID) {
        //TODO implement when the feature gets implemented
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) {
        //TODO implement when the feature gets implemented
        return null;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID) {
        //TODO implement when the feature gets implemented
        return null;
    }
}
