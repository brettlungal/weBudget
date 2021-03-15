package com.comp3350.webudget.persistence;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.comp3350.webudget.application.Main;
import com.comp3350.webudget.business.SignupLogic;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.presentation.LoginActivity;
import com.comp3350.webudget.presentation.MainActivity;
import com.comp3350.webudget.presentation.SignupActivity;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import android.content.Intent;



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
    public boolean accountExist(String username, String password){
        for(int i = 0; i < database.size(); i++){
            Account temp = database.get(i);
            if(temp.getUsername().equals(username) && temp.getPassword().equals(password))
                return true;
        }
        return false;
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
    public Account getAccount(String username) {
        for(int i = 0; i < database.size(); i++){
            Account temp = database.get(i);
            if(temp.getUsername().equals(username))
                return temp;
        }
        return null;
    }
}
