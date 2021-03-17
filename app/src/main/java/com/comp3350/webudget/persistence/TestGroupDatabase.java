package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.objects.Wallet;

import java.util.ArrayList;

public class TestGroupDatabase implements IGroupDatabase{

    //TODO implement this class fully
    //Only exists so it can be called without error by the Logic Layer

    ArrayList<Group> database;
    IWalletDatabase walletDatabase = null;
    int groupID = -1;

    public TestGroupDatabase(){
        database = new ArrayList<>();
        walletDatabase = Services.walletPersistence();
    }

    public TestGroupDatabase(IWalletDatabase injectedWalletDatabase){
        database = new ArrayList<>();
        walletDatabase = injectedWalletDatabase;
    }

    @Override
    public int createGroup(String groupName, ArrayList<String> memberNames) {
        groupID++;
        int walletID = walletDatabase.insertWallet(groupName);
        database.add(new Group(groupName, groupID, walletID, new ArrayList<>(memberNames)));
        return walletID;
    }

    @Override
    public Group getGroup(int id) {
        return database.get(id);
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        return new ArrayList<>(database);
    }

}
