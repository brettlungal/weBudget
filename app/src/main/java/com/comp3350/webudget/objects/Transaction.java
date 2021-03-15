package com.comp3350.webudget.objects;

public class Transaction {

    private int id;
    private int amount;
    private String title;
    private Account user;
    private String date;


    public Transaction(int id, String title, int amount, Account user, String date){
        this.title = title;
        this.amount = amount;
        this.user = user;
        this.date = date;
        this.id = id;
    }

    public int getId() {
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
