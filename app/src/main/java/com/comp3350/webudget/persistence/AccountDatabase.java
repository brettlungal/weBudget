package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.application.Main;
import com.comp3350.webudget.objects.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


import java.util.ArrayList;

public class AccountDatabase implements IAccountDatabase {
    ArrayList<Account> database;

    public AccountDatabase(){
    }

    private Connection connect() throws SQLException {
        System.out.println(Main.getDBPathName());
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ Main.getDBPathName() +"/SC;shutdown=true", "SA", "");
    }



    @Override
    public boolean accountExist(String username, String password)
    {
        boolean flag = false;
        try
        {
            PreparedStatement verifyAcc = connect().prepareStatement(
                    "select * from accounts where username = ? and password = ?"
            );

            flag = true;

            verifyAcc.setString(1, username);
            verifyAcc.setString(2, password);
            ResultSet resultSet = verifyAcc.executeQuery();

            while (resultSet.next() && flag)
            {
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");

                System.out.println(" Account of the User "+userName + " exists.");
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return flag;
    }

    @Override
    public void insertUser(String fName, String lName, String username, String password){
        //database.add(new Account(fName, lName, username, password, 0, null));
        String accounts = "create table if not EXISTS accounts( "+
                " username VARCHAR(100),"+
                " password VARCHAR(100),"+
                " fName VARCHAR(100),"+
                " lName VARCHAR(100),"+
                " primary key(username)" +
                ")";

        try {
            connect().createStatement().executeUpdate(accounts);

            PreparedStatement addAccount = connect().prepareStatement(
                    "insert into accounts (username,password, fName,lName) values (?, ?,?,?);"
            );

            addAccount.setString(1, username );
            addAccount.setString(2, password );
            addAccount.setString(3, fName);
            addAccount.setString(4, lName );

            addAccount.executeUpdate();

            addAccount.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        //database.add(new Account(fName, lName, username, password, 0, null));

    }

    @Override
    public Account getAccount(String username)
    {
        try
        {
            PreparedStatement getAccStatement = connect().prepareStatement(
                    "select * from accounts where username = ?"
            );

            getAccStatement.setString(1, username);
            ResultSet resultSet = getAccStatement.executeQuery();

            while (resultSet.next())
            {
                String userName = resultSet.getString("username");
                String firstName = resultSet.getString("fName");
                String lastName = resultSet.getString("lName");

                System.out.println(userName + " is associated with the account of " +firstName+" "+lastName);
            }
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Account> getAllAccounts()  throws AccountException {
        throw new AccountException("Method not implemented yet!");
    }
}

//for(int i = 0; i < database.size(); i++){
//            Account temp = database.get(i);
//            if(temp.getUsername().equals(username))
//                return temp;
//        }
//        return null;
//        for(int i = 0; i < database.size(); i++){
//            Account temp = database.get(i);
//            if(temp.getUsername().equals(username) && temp.getPassword().equals(password))
//                return true;
//        }
//        return false;