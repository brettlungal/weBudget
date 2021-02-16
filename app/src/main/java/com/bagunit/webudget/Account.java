package com.bagunit.webudget;

public class Account {

    private String fName;
    private String lName;
    private String email;
    private String password;
    private int age;
    private Wallet wallet;
    //private Group group;

    public Account(String fName, String lName, int age , Wallet wallet,String email, String password){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.wallet = wallet;
        this.email = email;
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAge() {
        return age;
    }

    public Wallet getWallet() {
        return wallet;
    }

}
