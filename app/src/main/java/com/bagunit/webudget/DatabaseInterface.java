package com.bagunit.webudget;

public interface DatabaseInterface {

    public void getUser(String email);
    public void insertUser(String fName,String lName,String Email,int walletID);
    public void insertWallet(int id,double balance,String ownerEmail);
    public void getWallet(int id);
    public void insertTransaction(int id,double amount);
    public void getTransaction(int id);
    public void insertGroup(int id);
    public void getGroup(int id);

}
