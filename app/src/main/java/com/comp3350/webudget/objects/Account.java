package com.comp3350.webudget.objects;

import java.util.ArrayList;

public class Account {

    private static int ID;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private int age;
    private Wallet wallet;
    private ArrayList<Group> groups;

    public Account(String fName, String lName, int age ,String username, String password){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.wallet = wallet;
        this.username = username;
        this.password = password;
        this.wallet = new Wallet(username);
        this.groups = new ArrayList<Group>();
        ID++;
    }

    public String getFirstName() {
        return fName;
    }

    public String getLastName() {
        return lName;
    }

    public int getAge() {
        return age;
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

    public int getID(){
        return ID;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
