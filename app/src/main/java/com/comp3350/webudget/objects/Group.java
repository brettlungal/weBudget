package com.comp3350.webudget.objects;
import java.util.ArrayList;
public class Group {


    private String name;
    private int id;
    private int walletID;
    private ArrayList<String> memberIDs;

    public Group(String name, int id, int walletID, ArrayList<String> memberIDs){
        this.name = name;
        this.id = id;
        this.memberIDs = memberIDs;
        this.walletID = walletID;
    }

    public ArrayList<String> getMemberIDs(){
        return this.memberIDs;
    }

    public int getId(){
        return this.id;
    }

    public int getWallet(){
        return this.walletID;
    }

    public String getName() { return this.name; }
}