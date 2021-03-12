package com.comp3350.webudget.objects;

public class Wallet {

    private static int walletID = 1;
    private String ownerName;
    private int balance;

    public Wallet(int walletID, String ownerName, int balance){
        this.walletID = walletID;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public double getBalance(){
        return this.balance;
    }
    public int getWalletID(){ return this.walletID; }

    public String toString(){
        return this.ownerName+" wallet contains "+balance;
    }

}
