package com.comp3350.webudget.business;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.GroupException;
import com.comp3350.webudget.objects.Account;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestAccountDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestGroupDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestMembershipDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestWalletDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GroupLogicTest {
    //Interface to test:
    /*
    public Group getGroup(int groupID)  throws GroupException;
    public ArrayList<Group> getGroups()  throws GroupException;
    public ArrayList<Group> getUserGroups(String username)  throws AccountException, GroupException;
    public ArrayList<Account> getGroupUsers(int groupID) throws AccountException, GroupException;
    public int createEmptyGroup(String name) throws GroupException;
    public int createGroupWithUsers(String name, ArrayList<String> usernames)  throws AccountException, GroupException;
    public void addUserToGroup(String username, int groupID)  throws AccountException, GroupException;
    public void removeUserFromGroup(String username, int groupID)  throws AccountException, GroupException;
     */
    //TODO flesh this out with more tests

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IWalletDatabase testWalletDB;
    private IAccountDatabase testAccountDB;
    private IGroupDatabase testGroupDB;
    private IMembershipDatabase testMembershipDB;
    private IGroupLogic testGroupLogic;

    private final String[] user1Input = {"xx", "xx", "user1", "password1"};
    private final String[] user2Input = {"yy", "yy", "user2", "password2"};

    @Before
    public void setUp(){
        testWalletDB = new TestWalletDatabase();
        testAccountDB = new TestAccountDatabase(testWalletDB);
        testGroupDB = new TestGroupDatabase(testWalletDB);
        testMembershipDB = new TestMembershipDatabase(testAccountDB, testGroupDB);
        testGroupLogic = new GroupLogic(testAccountDB, testGroupDB, testMembershipDB);
    }

    //getGroup: fails on no groups in DB
    @Test(expected = GroupException.class)
    public void getGroupButNoneExist() throws GroupException {
        testGroupLogic.getGroup(0);
    }

    //getGroup: fails on wrong groupIDt
    @Test(expected = GroupException.class)
    public void getGroupWrongID() throws GroupException {
        ArrayList<String> names = new ArrayList<String>();
        names.add("testUser1");
        names.add("testUser2");
        int correctID = testGroupDB.insertGroup("g1",names);
        testGroupLogic.getGroup((correctID+1));
    }

    //getGroup: succeeds with correct groupID (w/ assert not null)
    @Test
    public void getGroupSuccess() throws GroupException{
        ArrayList<String> names = new ArrayList<String>();
        names.add("testUser1");
        names.add("testUser2");
        int correctID = testGroupDB.insertGroup("g1", names);
        Group group = testGroupLogic.getGroup(correctID);
        assertEquals(group.getId(), correctID);
    }

    @Test
    public void verifyGroupCountZero() throws GroupException{
        ArrayList<Group> no_groups = testGroupLogic.getGroups();
        assertEquals(no_groups.size(),0);
    }

    @Test
    public void verifyGroupCount() throws GroupException{
        testGroupLogic.createEmptyGroup("test1");
        testGroupLogic.createEmptyGroup("test2");
        testGroupLogic.createEmptyGroup("test3");
        ArrayList<Group> all_groups = testGroupLogic.getGroups();
        assertEquals(all_groups.size(),3);
    }

    //getUserGroups: same kinda test cases as above
    @Test(expected = AccountException.class)
    public void getUsersGroupsInvalidUser() throws GroupException, AccountException {
        ArrayList<Group> no_groups = testGroupLogic.getUserGroups("nobody");
        assertEquals(no_groups.size(),0);
    }
    @Test
    public void getUserGroupsValidUserNoGroups() throws GroupException, AccountException{
        testAccountDB.insertUser("badinternet001", "Rob","Guderian","pass123");
        ArrayList<Group> no_groups = testGroupLogic.getUserGroups("badinternet001");
        assertEquals(no_groups.size(),0);
    }
    @Test
    public void getUserGroupsValidUser() throws GroupException, AccountException{
        testAccountDB.insertUser("badinternet001", "Rob","Guderian","pass123");
        ArrayList<String> users = new ArrayList<String>();
        users.add("badinternet001");
        int group_id = testGroupDB.insertGroup("test",users);
        testGroupLogic.addUserToGroup("badinternet001",group_id);
        ArrayList<Group> rob_groups = testGroupLogic.getUserGroups("badinternet001");
        assertEquals(rob_groups.size(),1);
    }

    @Test(expected = GroupException.class)
    public void getGroupUsersInvalidGroup() throws GroupException, AccountException{
        ArrayList<Account> members = testGroupLogic.getGroupUsers(12);
    }

    @Test
    public void getGroupUsersEmptyGroup()throws GroupException, AccountException{
        int empty_id = testGroupLogic.createEmptyGroup("nothing");
        ArrayList<Account> users = testGroupLogic.getGroupUsers(empty_id);
        assertEquals(0,users.size());
    }

    @Test
    public void getGroupUsersValidGroup()throws GroupException, AccountException{
        testAccountDB.insertUser("test", "first","name","easypass");
        int group_id = testGroupLogic.createEmptyGroup("nothing");
        testGroupLogic.addUserToGroup("test",group_id);
        ArrayList<Account> members = testGroupLogic.getGroupUsers(group_id);
        assertEquals(1,members.size());
    }

    @Test
    public void getGroupSizeUsers() throws GroupException, AccountException{
        ArrayList<String> users = new ArrayList<String>();
        users.add("user1");
        users.add("user2");
        int id = testGroupLogic.createGroupWithUsers("coolgroup",users);
        ArrayList<Account> members = testGroupLogic.getGroupUsers(id);
        assertEquals(2,members.size());
    }

    @Test
    public void getGroupSizeEmptyGroup() throws GroupException, AccountException{
        ArrayList<String> empty = new ArrayList<String>();
        int id = testGroupLogic.createGroupWithUsers("empty",empty);
        ArrayList<Account> users = testGroupLogic.getGroupUsers(id);
        assertEquals(0, users.size());
    }

    @Test(expected = NullPointerException.class)
    public void createGroupWithNull()throws GroupException,AccountException{
        int id = testGroupLogic.createGroupWithUsers("null",null);
    }


    //createGroupWithUsers: the group should not be made if any of the users do not exist. (you'll need two tests for this. One for accountError, and one to check that no group has been created) If you're clever you can do it in one

    @Test(expected = AccountException.class)
    public void addInvalidUserToExistingGroup()throws GroupException, AccountException{
        ArrayList<String> mem = new ArrayList<String>();
        int id = testGroupDB.insertGroup("testing",mem);
        testGroupLogic.addUserToGroup("nobody",id);
    }

    @Test
    public void addValidUserToExistingGroup()throws GroupException, AccountException{
        testAccountDB.insertUser("horidinternet","Rob","Guderian","pass");
        ArrayList<String> mem = new ArrayList<>();
        int group_id = testGroupDB.insertGroup("robsGroup",mem);
        testGroupLogic.addUserToGroup("horidinternet",group_id);
        ArrayList<Account> users = testGroupLogic.getGroupUsers(group_id);
        assertEquals(1,users.size());
    }

    @Test(expected = GroupException.class)
    public void addValidUserToInvalidGroup()throws GroupException,AccountException{
        testAccountDB.insertUser("horidinternet","Rob","Guderian","pass");
        testGroupLogic.addUserToGroup("horidinternet",420);
    }

    @Test
    public void addValidUserToPopulatedGroup()throws GroupException,AccountException{
        testAccountDB.insertUser("horidinternet","Rob","Guderian","pass");
        ArrayList<String> users = new ArrayList<>();
        users.add("horidinternet");
        int group_id = testGroupLogic.createGroupWithUsers("bobby",users);
        testAccountDB.insertUser("dude","Random","Dude","bigpassword");
        testGroupLogic.addUserToGroup("dude",group_id);
        ArrayList<Account> mem = testGroupLogic.getGroupUsers(group_id);
        assertEquals(mem.size(),2);
    }



}
