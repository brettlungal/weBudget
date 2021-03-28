package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class AccountDatabase implements IAccountDatabase {

    private final String dbPath;
    private IWalletDatabase walletDatabase;


    public AccountDatabase(final String dbPath){
        this.dbPath = dbPath;
        this.walletDatabase = Services.walletPersistence();
    }

    public AccountDatabase(final String dbPath, IWalletDatabase injectedWalletDatabase){
        this.dbPath = dbPath;
        walletDatabase = injectedWalletDatabase;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public void insertUser(String username, String fName, String lName, String password){
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "insert into accounts (username,password, fName,lName) values (?, ?,?,?);"
            );

            st.setString(1, username );
            st.setString(2, password );
            st.setString(3, fName);
            st.setString(4, lName );
            st.executeUpdate();
            st.close();

            int walletID = walletDatabase.insertWallet(username);

            final PreparedStatement st2 = c.prepareStatement(
                    "update accounts set walletid=? where username=?;"
            );

            st2.setInt(1, walletID );
            st2.setString(2, username );
            st2.executeUpdate();
            st2.close();


        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Account getAccount(String username){
        //retrieve account from database
        Account toReturn = null;
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(
                    "select * from accounts where username = ?"
            );
            st.setString(1, username);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");
                int walletID = resultSet.getInt("walletid");
                toReturn = new Account(firstName,lastName,userName,password,walletID,null);
            }
            st.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return toReturn;
    }

    public ArrayList<Account> getAllAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(
                    "select * from accounts"
            );
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");
                accounts.add(new Account(firstName,lastName,userName,password,-1,null));
            }
            st.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return accounts;
    }

}
