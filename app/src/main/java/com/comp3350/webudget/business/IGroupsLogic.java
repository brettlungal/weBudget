package com.comp3350.webudget.business;

import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public interface IGroupsLogic {
    public Group getGroup(int id)  throws GroupException;
    public ArrayList<Group> getGroups()  throws GroupException;
    public ArrayList<Group> getUserGroups(String username)  throws GroupException;
    public void createEmptyGroup(String name) throws GroupException;
    public void createGroupWithUsers(String name, ArrayList<String> usernames)  throws GroupException;
    public void addUserToGroup(String username, int groupID)  throws GroupException;
    public void removeUserFromGroup(String username, int groupID)  throws GroupException;

    //TODO add admins maybe?
}