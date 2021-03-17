package com.comp3350.webudget.persistence;

import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import java.util.ArrayList;

public class TestGroupDatabase implements IGroupDatabase{

    //TODO implement this class fully
    //Only exists so it can be called without error by the Logic Layer

    ArrayList<Account> database;
    IWalletDatabase walletDatabase = null;

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
        return 0;
    }

    @Override
    public Group getGroup(int id) {
        return null;
    }

    @Override
    public ArrayList<Group> getAllGroups() {
        return null;
    }

    @Override
    public ArrayList<Group> getUserGroups(String username) {
        return null;
    }

    @Override
    public void addUserToGroup(String username, int groupID) {

    }

    @Override
    public void removeUserFromGroup(String username, int groupID) {

    }
}
