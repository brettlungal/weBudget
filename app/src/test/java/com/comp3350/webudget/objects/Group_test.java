package com.comp3350.webudget.objects;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Group_test {

    private Group group;

    @Before
    public void setUp(){
        group = new Group("testGroup",1,1);
    }

    @Test
    public void test_addToGroup(){
        //testing successful addition of user accounts
        //Account test_user = new Account("test","user","test_user@gmail.com","plain-text-pwrd",1,null);
        //group.addMember(test_user);
        //ArrayList<Account> members = group.getMembers();
        //assertEquals("List length should be 1",1,members.size());
    }

}
