package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IGroupDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDatabase implements IGroupDatabase {

    private final String dbPath;

    public GroupDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

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
