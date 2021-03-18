package com.comp3350.webudget.persistence.hsqldb;
import androidx.core.app.ActivityCompat;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.persistence.IAccountDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class AccountDatabase implements IAccountDatabase {

    private final String dbPath;

    public AccountDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public void insertUser(String fName, String lName, String username, String password){
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
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Account getAccount(String username){
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
                return new Account(firstName,lastName,userName,password,-1,null);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
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
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return accounts;
    }

}
