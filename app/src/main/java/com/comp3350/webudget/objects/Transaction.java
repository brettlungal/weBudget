package com.comp3350.webudget.objects;

public class Transaction {

    private static int id = 1;
    private double amount;
    private String title;
    private Account user;
    private String date;


    public Transaction(String title, double amount, Account user, String date){
        this.title = title;
        this.amount = amount;
        this.user = user;
        this.date = date;
        this.id = id++;
    }

    public static int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getTitle() {
        return title;
    }

    public Account getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }


}
