package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public interface IGroupDatabase {
     public int insertGroup(String groupName, ArrayList<String> memberNames);
     public Group getGroup(int id) throws GroupException;
     public ArrayList<Group> getAllGroups();
}
