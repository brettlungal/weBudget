package com.comp3350.webudget.objects;

public class Wallet {

    private int walletID;
    private String ownerName;
    private int balance;

    public Wallet(int walletID, String ownerName, int balance){
        this.walletID = walletID;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getWalletID(){
        return this.walletID;
    }

    public double getBalance(){
        return this.balance;
    }

    public String toString(){
        return this.ownerName+" wallet contains "+balance;
    }



}
