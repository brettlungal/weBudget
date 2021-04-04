package com.comp3350.webudget.persistence.hsqldb;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Transaction;
import com.comp3350.webudget.persistence.ITransactionDatabase;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDatabase implements ITransactionDatabase {

    private final String dbPath;


    public TransactionDatabase(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:"+ dbPath +";shutdown=true", "SA", "");
    }


    @Override
    public void insertTransaction(int fromWalletid, int toWalletid, int amount) {
        try(final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(
                    "insert into transaction (fromid,toid, amount,date) values (?, ?,?,?);"
            );

            st.setInt(1, fromWalletid );
            st.setInt(2, toWalletid );
            st.setInt(3, amount);
            st.setString(4, "N/A" );
            st.executeUpdate();
            st.close();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public ArrayList<Transaction> getInputTransaction(int walletID) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(
                    "select * from account where toid = ?"
            );
            st.setInt(1, walletID);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                int tID = resultSet.getInt("transactionid");
                int fromid = resultSet.getInt("fromid");
                int toid = resultSet.getInt("toid");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("date");
                transactions.add(new Transaction(tID,fromid,toid,amount,date));
            }
            st.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return transactions; // return an empty ArrayList if no transaction given walletid
    }

    @Override
    public ArrayList<Transaction> getOutputTransaction(int walletID) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try(final Connection c = connection()){
            final PreparedStatement st = c.prepareStatement(
                    "select * from account where fromid = ?"
            );
            st.setInt(1, walletID);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                int tID = resultSet.getInt("transactionid");
                int fromid = resultSet.getInt("fromid");
                int toid = resultSet.getInt("toid");
                int amount = resultSet.getInt("amount");
                String date = resultSet.getString("date");
                transactions.add(new Transaction(tID,fromid,toid,amount,date));
            }
            st.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return transactions; // return an empty ArrayList if no transaction given walletid
    }
}
