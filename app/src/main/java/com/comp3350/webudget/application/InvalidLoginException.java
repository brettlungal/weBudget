package com.comp3350.webudget.application;

public class InvalidLoginException extends Exception{

    public InvalidLoginException(){
        super();
    }

    public InvalidLoginException(String message){
        super(message);
    }
}
