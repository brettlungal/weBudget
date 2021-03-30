package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public interface IMembershipDatabase {
    public Boolean isUserInGroup(String username, int groupID) throws AccountException, GroupException;
    public void addUserToGroup(String username, int groupID) throws AccountException, GroupException;
    public void removeUserFromGroup(String username, int groupID) throws AccountException, GroupException;
    public ArrayList<Group> getUserGroups(String username) throws AccountException, GroupException;
    public ArrayList<Account> getGroupUsers(int groupID) throws AccountException, GroupException;
}
