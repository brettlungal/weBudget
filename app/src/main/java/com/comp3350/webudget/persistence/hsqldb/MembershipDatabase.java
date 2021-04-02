package com.comp3350.webudget.persistence.hsqldb;


import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.application.Services;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipDatabase implements IMembershipDatabase {

    private final String dbPath;
    private IGroupDatabase groupDatabase;
    private IAccountDatabase accountDatabase;


    public MembershipDatabase(final String dbPath){
        this.dbPath = dbPath;
        this.groupDatabase = Services.groupPersistence();
        this.accountDatabase = Services.accountPersistence();
    }

    public MembershipDatabase(final String dbPath, IGroupDatabase injectedGroupDatabase, IAccountDatabase injectedAccountDatabase){
        this.dbPath = dbPath;
        groupDatabase = injectedGroupDatabase;
        accountDatabase = injectedAccountDatabase;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public Boolean isUserInGroup(String username, int groupID){
        return null;
    }

    @Override
    public void addUserToGroup(String username, int groupID){
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
    public ArrayList<Group> getUserGroups(String username) throws GroupException{
        ArrayList<Group> groups = new ArrayList<>();

        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "select groupid from membership where username=?;"
            );
            st.setString(1, username );
            ResultSet resultSet = st.executeQuery();
            st.close();
            while(resultSet.next()){
                groups.add(groupDatabase.getGroup(resultSet.getInt("groupid")));
            }

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }catch (final GroupException accountException){
            throw new GroupException("Error in Membership Database When Getting Groups");
        }
        return groups;
    }

    @Override
    public ArrayList<Account> getGroupUsers(int groupID) throws AccountException{
        ArrayList<Account> accounts = new ArrayList<>();

        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "select username from membership where groupid=?;"
            );
            st.setInt(1, groupID);
            ResultSet resultSet = st.executeQuery();
            st.close();
            while(resultSet.next()){
                accounts.add(accountDatabase.getAccount(resultSet.getString("username")));
            }

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }catch (final AccountException accountException){
            throw new AccountException("Error in Membership Database When Getting User Account");
        }
        return accounts;
    }
}