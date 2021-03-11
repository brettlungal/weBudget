package com.comp3350.webudget.persistence;

import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Wallet;

import java.util.ArrayList;

public class TestWalletDatabase implements IWalletDatabase{
    ArrayList<Wallet> database;
    static int walletID = 0;

    public TestWalletDatabase(){
        database = new ArrayList<>();
    }

    @Override
    public int insertWallet(String ownerName) {
        walletID++;
        database.add(new Wallet(walletID, ownerName, 0));
        return walletID;
    }

    @Override
    public Wallet getWallet(int id) {
        for(int i = 0; i < database.size(); i++){
            Wallet temp = database.get(i);
            if(temp.getWalletID() == id)
                return temp;
        }
        return null;
    }
}
