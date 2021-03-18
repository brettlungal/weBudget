package com.comp3350.webudget.business;

import com.comp3350.webudget.application.SignupException;
import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.IGroupDatabase;
import com.comp3350.webudget.persistence.IMembershipDatabase;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.TestAccountDatabase;
import com.comp3350.webudget.persistence.TestGroupDatabase;
import com.comp3350.webudget.persistence.TestMembershipDatabase;
import com.comp3350.webudget.persistence.TestWalletDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        testGroupLogic = new GroupLogic(testAccountDB, testGroupDB);
    }

    //getGroup: fails on no groups in DB
    //getGroup: fails on wrong groupID
    //getGroup: succeeds with correct groupID (w/ assert not null)

    //getGroups: returns an arrayList of 0 groups if none are in the database (assert not null first)
    //getGroups: returns an arrayList of N groups if N groups are added to the database (test will be deterministic with random N)
    //          --> mix createEmptyGroup and createGroupWithUsers

    //getUserGroups: same kinda test cases as above

    //getGroupUsers: same kinda test cases as above

    //createEmptyGroup: the group should have no members to begin with (a non-null list)

    //createGroupWithUsers: the group should have the same size as the number of UNIQUE users
    //createGroupWithUsers: passing an empty arraylist should have the same result as createEmptyGroup
    //createGroupWithUsers: passing a null list should result in an error
    //createGroupWithUsers: the group should not be made if any of the users do not exist. (you'll need two tests for this. One for accountError, and one to check that no group has been created) If you're clever you can do it in one

    //addUserToGroup: test with fake users, fake groups, and both
    //addUserToGroup: Test everything on empty & non empty groups. For non empty groups, test cases of if the user is in the initial group or not
    //addUserToGroup: for all cases, when a new user is added, the size of the Group's Users and User's Groups lists should increase by 1
    //addUserToGroup: for all cases, if the same user is added twice, the size of the membership lists should not change
    //addUserToGroup: test with multiple users being added

    //removeUserFromGroup: aaaaaaaaaa probably a bunch of things

    @Test
    public void signUpSuccess() throws SignupException {
        testUserLogic.signUp(user1Input);
    }

    @Test(expected = SignupException.class)
    public void signUpFail() throws SignupException {
        testUserLogic.signUp(user1Input);
        testUserLogic.signUp(user1Input);
    }
}
