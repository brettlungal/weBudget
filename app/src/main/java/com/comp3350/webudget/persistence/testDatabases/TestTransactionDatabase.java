package com.comp3350.webudget.persistence.testDatabases;

import com.comp3350.webudget.objects.Transaction;
import com.comp3350.webudget.persistence.ITransactionDatabase;

import java.util.ArrayList;

public class TestTransactionDatabase implements ITransactionDatabase {

    ArrayList<Transaction> database;
    static int id = 0;

    public TestTransactionDatabase(){
        database = new ArrayList<>();
    }


    @Override
    public void insertTransaction(int fromWalletid, int toWalletid, int amount) {
        database.add(new Transaction(id,fromWalletid,toWalletid,amount,"today"));
        id++;
    }

    @Override
    public ArrayList<Transaction> getInputTransaction(int walletID) {
        ArrayList<Transaction> transactionsIN = new ArrayList<>();
        for( Transaction transaction : database ){
            if ( transaction.getToWalletid() == walletID ){
                transactionsIN.add(transaction);
            }
        }
        return transactionsIN;
    }

    @Override
    public ArrayList<Transaction> getOutputTransaction(int walletID) {
        ArrayList<Transaction> transactionsOUT = new ArrayList<>();
        for( Transaction transaction : database ){
            if ( transaction.getFromWalletid() == walletID ){
                transactionsOUT.add(transaction);
            }
        }
        return transactionsOUT;
    }
}
