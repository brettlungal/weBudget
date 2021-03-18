package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IGroupDatabase;

import java.util.ArrayList;

public class GroupDatabase implements IGroupDatabase {

    //TODO implement this class fully
    //Only exists so it can be called without error by the UI

    @Override
    public int insertGroup(String groupName, ArrayList<String> memberNames) {
        return 0;
    }

    @Override
    public Group getGroup(int id) {
        return null;
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        return null;
    }
}
