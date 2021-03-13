package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.Main;
import com.comp3350.webudget.objects.Wallet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;

public class WalletDatabase implements IWalletDatabase
{
    ArrayList<Wallet> walletDatabase;

    public WalletDatabase()
    {
    }

    private Connection connect() throws SQLException
    {
        System.out.println(Main.getDBPathName());
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ Main.getDBPathName() +"/SC;shutdown=true", "SA", "");
    }

    @Override
    public int insertWallet(String username) {
        int id = 0;
        double balance = 0;
        String wallet = "create table wallet( "+
                " id integer,"+
                " balance VARCHAR(100),"+
                " username VARCHAR(100),"+
                " primary key(username)" +
                " foreign key (username) references accounts);"+
                ")";

        try {
            connect().createStatement().executeUpdate(wallet);


            PreparedStatement addWallet = connect().prepareStatement(
                    "insert into wallet username (id, balance, username) values (?, ?, ?);"
            );

            addWallet.setInt(1, id );
            addWallet.setDouble(2, balance );
            addWallet.setString(3, username);


            addWallet.executeUpdate();

            addWallet.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return 0;
    }

    @Override
    public Wallet getWallet(int id) {
        for(int i = 0; i < walletDatabase.size(); i++){
            Wallet temp = walletDatabase.get(i);
            if(temp.getWalletID()==(id))
                return temp;
        }
        return null;
    }
}
