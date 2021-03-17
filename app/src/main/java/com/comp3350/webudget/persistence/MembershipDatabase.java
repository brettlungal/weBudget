package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public class MembershipDatabase implements IMembershipDatabase {
    @Override
    public void addUserToGroup(String username, int groupID) {

    }

    @Override
    public void removeUserFromGroup(String username, int groupID) {

    }

    @Override
    public ArrayList<Group> getUserGroups(String username) {
        return null;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID) {
        return null;
    }
}
