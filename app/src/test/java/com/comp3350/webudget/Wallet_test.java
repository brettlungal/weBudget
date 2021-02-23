package com.comp3350.webudget;
import com.comp3350.webudget.objects.Wallet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Wallet_test {

    private Wallet wallet;
    @Before
    protected void setUp(){
        wallet = new Wallet("test_owner");
    }

    @Test
    public void test_addMoney(){
        wallet.addMoney(10.5);
        assertEquals("balance should equal 10.5",10.5,wallet.getBalance());
    }

    @Test
    public void test_removeMoneyValid(){
        wallet.removeMoney(8);
        assertEquals("balance should equal 2.5",2.5,wallet.getBalance());
    }

    public void test_removeMoneyInvalid(){
        wallet.removeMoney(100);
        assertEquals("balance should equal 10.5",10.5,wallet.getBalance());
    }
}
