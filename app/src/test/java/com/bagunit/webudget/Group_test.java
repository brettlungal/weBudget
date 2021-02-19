package com.bagunit.webudget;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Group_test {

    private Group group;

    @Before
    protected void setUp(){
        group = new Group("testGroup");
    }

    @Test
    public void test_addToGroup(){
        //testing successful addition of user accounts
        Account test_user = new Account("test","user",21,"test_user@gmail.com","plain-text-pwrd");
        group.addMember(test_user);
        ArrayList<Account> members = group.getMembers();
        assertEquals("List length should be 1",1,members.size());
    }

}
