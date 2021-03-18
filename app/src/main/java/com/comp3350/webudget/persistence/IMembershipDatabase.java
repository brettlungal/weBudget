package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public interface IMembershipDatabase {
    public void addUserToGroup(String username, int groupID);
    public void removeUserFromGroup(String username, int groupID);
    public ArrayList<Group> getUserGroups(String username);
    public ArrayList<Account> getGroupUsers(int groupID);
}
