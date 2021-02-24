package com.comp3350.webudget.objects;

import java.util.ArrayList;

public class Account {

    private String fName;
    private String lName;
    private String username;
    private String password;
    private Wallet wallet;
    private ArrayList<Group> groups;

    public Account(String fName, String lName, String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.wallet = wallet;
        this.username = username;
        this.password = password;
        this.wallet = new Wallet(username);
        this.groups = new ArrayList<Group>();
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public ArrayList<Group> getGroups(){
        return this.groups;
    }

    public void addToGroup(Group newGroup){
        groups.add(newGroup);
        newGroup.addMember(this);
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
