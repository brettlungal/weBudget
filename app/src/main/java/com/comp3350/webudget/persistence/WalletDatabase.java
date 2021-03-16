package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.Main;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class WalletDatabase implements IWalletDatabase
{
    ArrayList<Wallet> walletDatabase;

    private Connection connect() throws SQLException
    {
        System.out.println(Main.getDBPathName());
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ Main.getDBPathName() +"/SC;shutdown=true", "SA", "");
    }

    @Override
    public int insertWallet(String username)
    {
        int walletID = 0;
        double balance = 0;

        String wallet = "create table wallet( "+
                "id INTEGER IDENTITY PRIMARY KEY,"+
                " username VARCHAR(100),"+
                " balance VARCHAR(100),"+
                " foreign key (username) references accounts);"+
                ")";

        try {
            connect().createStatement().executeUpdate(wallet);


            PreparedStatement addWallet = connect().prepareStatement(
                    "insert into wallet (username, balance) values (?, ?);"
            );

            //addWallet.setInt(1, id );
            addWallet.setString(1, username);
            addWallet.setDouble(2, balance );

            addWallet.executeUpdate();

            PreparedStatement psIdentity = connect().prepareStatement("CALL IDENTITY()");
            ResultSet result = psIdentity.executeQuery();
            walletID = result.getInt(1);
            System.out.println("The ID of the new wallet that is added is: "+walletID);

            addWallet.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return walletID;
    }

    @Override
    public Wallet getWallet(int id) {
            try
            {
                PreparedStatement getWalStatement = connect().prepareStatement(
                        "select * from wallet where id = ?"
                );

                getWalStatement.setInt(1, id);
                ResultSet resultSet = getWalStatement.executeQuery();

                while (resultSet.next())
                {
                    String userName = resultSet.getString("username");
                    double balance = resultSet.getDouble("balance");
                    System.out.println(userName + " with ID: "+id+"has a total balance: " +balance);
                }
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return null;
    }

    @Override
    public void deposit(int walletID, double amount) throws WalletException {
            try
            {
                PreparedStatement depositSt = connect().prepareStatement(
                        "update wallet set balance = balance + ? where id = ?"
                );

                depositSt.setDouble(1, amount);
                depositSt.setInt(2, walletID);
                ResultSet resultSet = depositSt.executeQuery();

                System.out.println("The amount has been deposited.");
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
    }

    @Override
    public void withdraw(int walletID, double amount) throws WalletException {
            try
            {
                PreparedStatement depositSt = connect().prepareStatement(
                        "update wallet set balance = balance - ? where id = ?"
                );

                depositSt.setDouble(1, amount);
                depositSt.setInt(2, walletID);
                ResultSet resultSet = depositSt.executeQuery();

                System.out.println("The amount has been withdrawn.");
            }
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }
}

//        for(int i = 0; i < walletDatabase.size(); i++){
//            Wallet temp = walletDatabase.get(i);
//            if(temp.getWalletID()==(id))
//                return temp;
//        }
//        return null;
