package com.bagunit.webudget;
import java.util.ArrayList;
public class Group {

    private ArrayList<Account> members;
    private String name;
    private int id;
    private Wallet wallet;

    public Group(String name,int id){
        this.name = name;
        this.id = id;
        members = new ArrayList<Account>();
        wallet = new Wallet(name);
    }

    public void addMember(Account newUser){
        this.members.add(newUser);
    }

    public ArrayList<Account> getMembers(){
        return this.members;
    }

    public int getId(){
        return this.id;
    }

    public Wallet getWallet(){
        return this.wallet;
    }
}
