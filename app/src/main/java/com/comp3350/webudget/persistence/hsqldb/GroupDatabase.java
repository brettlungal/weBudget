package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupDatabase implements IGroupDatabase {

    private final String dbPath;
    private IWalletDatabase walletDatabase;
    private IMembershipDatabase membershipDatabase;

    public GroupDatabase(final String dbPath){
        this.dbPath = dbPath;
        this.walletDatabase = Services.walletPersistence();
        membershipDatabase = Services.membershipPersistence();
    }

    public GroupDatabase(final String dbPath, IWalletDatabase injectedWalletDatabase, IAccountDatabase injectedAccountDatabase){
        this.dbPath = dbPath;
        walletDatabase = injectedWalletDatabase;
        membershipDatabase = new MembershipDatabase(dbPath, this, injectedAccountDatabase);
    }


    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public int insertGroup(String groupName) {
        int walletID = walletDatabase.insertWallet(groupName);
        int groupID = -1;
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "insert into groupTable (name,walletid) values (?, ?);"
            );

            st.setString(1, groupName );
            st.setInt(2, walletID );
            st.executeUpdate();
            st.close();

            final PreparedStatement st2 = c.prepareStatement(
                    "select MAX(groupid) as maxID from groupTable;"
            );
            ResultSet resultSet = st2.executeQuery();
            if (resultSet.next()){
                groupID = resultSet.getInt("maxID");
            }
            st2.close();


        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return groupID;
    }

    @Override
    public Group getGroup(int id) throws GroupException {
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(
                    "select * from account where groupid = ?"
            );
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                String groupName = resultSet.getString("name");
                int walletid = resultSet.getInt("walletid");
                return new Group(groupName,id,walletid,getAllUsername(id));
            }
            st.close();
        }
        catch (SQLException sqlException) {
            throw new GroupException("Fail to Get Account in Database");
        }
        return null;
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        //TODO implement when the feature gets implemented
        return null;
    }


    private ArrayList<String> getAllUsername(int groupID) throws GroupException{
        ArrayList<String> members = new ArrayList<>();
        try {
            ArrayList<Account> accounts = membershipDatabase.getGroupUsers(groupID);
            for(int i = 0; i < accounts.size(); i++){
                members.add(accounts.get(i).getUsername());
            }
        }catch (AccountException e){
            throw new GroupException("Error");
        }
        return members;
    }
}
