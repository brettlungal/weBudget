package com.bagunit.webudget;

public class Wallet {

    private static int walletID = 1;
    String ownerName;
    double balance;

    public Wallet(String ownerName){
        this.walletID = walletID;
        this.ownerName = ownerName;
        this.balance = 0;
    }

    public double getBalance(){
        return this.balance;
    }

    public void addMoney(double amount){
        if ( amount < 0 ){
            System.out.println("cannot add a negative amount to wallet. Please use removeMoney function");
        }else {
            this.balance += amount;
        }
    }
    public void removeMoney(double amount){
        if ( amount <= 0 ){
            System.out.println("Cannot remove negative amount from wallet. Please use addMoney function");
        }else{
            //verified input
            if ( this.balance-amount < 0 ){
                System.out.println("Sorry, insufficent funds");
            }else {
                //ok we can subtract the amount
                this.balance-=amount;
            }
        }
    }

    public String toString(){
        return this.ownerName+" wallet contains "+balance;
    }



}
