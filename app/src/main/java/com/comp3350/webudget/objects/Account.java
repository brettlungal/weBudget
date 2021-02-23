package com.comp3350.webudget.objects;

import java.util.ArrayList;

public class Account {

    private String fName;
    private String lName;
    private String email;
    private String password;
    private int age;
    private Wallet wallet;
    private ArrayList<Group> groups;

    public Account(String fName, String lName, int age ,String email, String password){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.wallet = wallet;
        this.email = email;
        this.password = password;
        this.wallet = new Wallet(email);
        this.groups = new ArrayList<Group>();
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

}
