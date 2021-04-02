package com.comp3350.webudget.persistence.hsqldb;


import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipDatabase implements IMembershipDatabase {

    private final String dbPath;
    private IGroupDatabase groupDatabase;


    public MembershipDatabase(final String dbPath){
        this.dbPath = dbPath;
        this.groupDatabase = Services.groupPersistence();
    }

    public MembershipDatabase(final String dbPath, IGroupDatabase injectedGroupDatabase){
        this.dbPath = dbPath;
        groupDatabase = injectedGroupDatabase;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public Boolean isUserInGroup(String username, int groupID) throws AccountException, GroupException {
        return null;
    }

    @Override
    public void addUserToGroup(String username, int groupID) {
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "insert into membership (username, groupid) values (?, ?);"
            );

            st.setString(1, username );
            st.setInt(2, groupID );
            st.executeUpdate();
            st.close();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void removeUserFromGroup(String username, int groupID) {
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "delete from membership where username=? and groupid=?;"
            );
            st.setString(1, username );
            st.setInt(2, groupID );
            st.executeUpdate();
            st.close();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) {
        ArrayList<Group> userGroupIds = new ArrayList<>();

        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "select groupid from membership where username=?;"
            );
            st.setString(1, username );
            st.executeUpdate();
            st.close();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID) {
        //TODO implement when the feature gets implemented
        return null;
    }
}