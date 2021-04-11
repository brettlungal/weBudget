package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.AccountException;
import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.Exceptions.MembershipException;
import com.comp3350.webudget.Exceptions.SignupException;
import com.comp3350.webudget.objects.Group;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.hsqldb.AccountDatabase;
import com.comp3350.webudget.persistence.hsqldb.GroupDatabase;
import com.comp3350.webudget.persistence.hsqldb.MembershipDatabase;
import com.comp3350.webudget.persistence.hsqldb.WalletDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestMembershipDatabase;
import com.comp3350.webudget.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GroupLogicIT {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IAccountDatabase testAccountDB;
    private IWalletDatabase testWalletDB;
    private IGroupDatabase testGroupDB;
    private IMembershipDatabase testMembershipDB;
    private IUserLogic testUserLogic;
    private IGroupLogic testGroupLogic;



    private final String[] user1Input = {"user1", "xx", "xx", "password1"};
    private final String[] user2Input = {"user2", "yy", "yy", "password2"};

    private final String group1Input = "testGroup1";
    private final String group2Input = "testGroup2";

    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        testWalletDB = new WalletDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
        testAccountDB = new AccountDatabase(this.tempDB.getAbsolutePath().replace(".script", ""), testWalletDB);
        testMembershipDB = new MembershipDatabase(this.tempDB.getAbsolutePath().replace(".script", ""), testAccountDB);
        testGroupDB = new GroupDatabase(this.tempDB.getAbsolutePath().replace(".script", ""), testWalletDB,testMembershipDB);
        testGroupLogic = new GroupLogic(testAccountDB, testGroupDB, testMembershipDB);
        testUserLogic = new UserLogic(this.testAccountDB);
    }

    @Test
    public void createMultipleGroups() throws GroupException {
        testGroupLogic.createEmptyGroup(group1Input);
        testGroupLogic.createEmptyGroup(group2Input);
    }

    @Test
    public void testGroupWithUsers() throws GroupException, AccountException, SignupException {
        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("user1");
        usernames.add("user2");
        testUserLogic.signUp(user1Input);
        testUserLogic.signUp(user2Input);
        testGroupLogic.createGroupWithUsers(group1Input,usernames);
    }


    @Test
    public void removeUserFromGroupTest() throws GroupException, AccountException, SignupException, MembershipException {
        ArrayList<String> usernames = new ArrayList<String>();
        usernames.add("user1");
        usernames.add("user2");
        testUserLogic.signUp(user1Input);
        testUserLogic.signUp(user2Input);
        testGroupLogic.removeUserFromGroup("user1",testGroupLogic.createGroupWithUsers(group1Input,usernames));
        testGroupLogic.removeUserFromGroup("user2",testGroupLogic.createGroupWithUsers(group1Input,usernames));
    }

    @Test
    public void addUserToGroupTest() throws GroupException, AccountException, MembershipException, SignupException {
        int groupID = testGroupLogic.createEmptyGroup(group1Input);
        testUserLogic.signUp(user1Input);
        testGroupLogic.addUserToGroup("user1", groupID);
    }

    @Test
    public void verifyGroupCount() throws GroupException{
        testGroupLogic.createEmptyGroup("test1");
        testGroupLogic.createEmptyGroup("test2");
        testGroupLogic.createEmptyGroup("test3");
        ArrayList<Group> all_groups = testGroupLogic.getGroups();
        System.out.println(all_groups.get(0).toString());
        assertEquals(3,all_groups.size());
    }

    @Test
    public void getUserGroupsValidUser() throws GroupException, AccountException, MembershipException{
        testAccountDB.insertUser("badinternet001", "Rob","Guderian","pass123");
        ArrayList<String> users = new ArrayList<String>();
        users.add("badinternet001");
        int group_id = testGroupLogic.createGroupWithUsers("test",users);
        ArrayList<Group> rob_groups = testGroupLogic.getUserGroups("badinternet001");
        assertEquals(1,rob_groups.size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}