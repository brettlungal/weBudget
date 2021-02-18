package com.bagunit.webudget;

import java.util.LinkedList;

public class Account {

    private String fName;
    private String lName;
    private String email;
    private String password;
    private int age;
    private Wallet wallet;
    private LinkedList<Group> groups;

    public Account(String fName, String lName, int age ,String email, String password){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.wallet = wallet;
        this.email = email;
        this.password = password;
        this.wallet = new Wallet(email);
        this.groups = new LinkedList<Group>();
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

    public LinkedList<Group> getGroups(){
        return this.groups;
    }

    public void addToGroup(Group newGroup){
        groups.add(newGroup);
    }

}
