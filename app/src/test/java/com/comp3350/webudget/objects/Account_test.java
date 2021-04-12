package com.comp3350.webudget.objects;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Account_test {

    private Account acct;
    @Before
    public void setUp(){
        acct = new Account("test","user","username","secure_pwrd", 1);
    }

    /*
    @Test
    public void test_addGroupToUserList(){
        //testing that the group is added to the users list of groups
        Group g = new Group("test");
        acct.addToGroup(g);
        ArrayList<Group> groups = acct.getGroups();
        assertEquals("List length should be 1",1,groups.size());
    }

    @Test
    public void test_addUserToGroupList(){
        //testing that the user is added to the group
        Group g = new Group("test");
        acct.addToGroup(g);
        ArrayList<Account> members = g.getMembers();
        assertEquals("User list length should be 1",1,members.size());
    }

    */
}
