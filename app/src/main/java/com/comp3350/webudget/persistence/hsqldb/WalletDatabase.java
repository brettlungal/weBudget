package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.objects.Wallet;
import com.comp3350.webudget.persistence.IWalletDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class WalletDatabase implements IWalletDatabase{
    private final String dbPath;


    public WalletDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }

    @Override
    public int insertWallet(String username){
        int walletID = -1;
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "insert into wallet (username,balance) values (?, ?);"
            );
            st.setString(1, username );
            st.setInt(2, 0 );
            st.executeUpdate();
            st.close();

            final PreparedStatement st2 = c.prepareStatement(
                    "select walletid from wallet where username=?;"
            );
            st2.setString(1, username);
            ResultSet resultSet = st2.executeQuery();
            if (resultSet.next()){
                walletID = resultSet.getInt("walletid");
            }
            st2.close();

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return walletID;
    }

    @Override
    public Wallet getWallet(int id) {
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "select * from wallet where walletid=?;"
            );
            st.setInt(1, id );
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                int walletID = resultSet.getInt("walletid");
                String username = resultSet.getString("username");
                int balance = resultSet.getInt("balance");
                return new Wallet(walletID,username,balance);
            }
            st.close();

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public void deposit(int walletID, int amount) throws WalletException {
        setBalance(walletID,getBalance(walletID) + amount);
    }

    @Override
    public void withdraw(int walletID, int amount) throws WalletException {
        setBalance(walletID, getBalance(walletID) - amount);
    }

    private int getBalance(int walletID){
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "select balance from wallet where walletid=?;"
            );
            st.setInt(1, walletID );
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("balance");
            }
            st.close();

        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return 0;
    }

    private void setBalance(int walletID, int amount){
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "update WALLET set balance=? where walletid=?;"
            );

            st.setInt(1, amount );
            st.setInt(2, walletID);
            st.executeUpdate();
            st.close();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}