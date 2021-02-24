package com.comp3350.webudget.persistence;

public interface ITransactionDatabase {
    public void insertTransaction(int id,double amount);
    public void getTransaction(int id);
}
