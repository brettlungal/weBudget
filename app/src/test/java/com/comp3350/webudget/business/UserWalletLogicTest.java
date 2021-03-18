package com.comp3350.webudget.business;

import static org.junit.Assert.assertEquals;

public class UserWalletLogicTest {

//    @Rule
//    public ExpectedException exception = ExpectedException.none();
//
//    private IAccountDatabase testAccountDB;
//    private IWalletDatabase testWalletDB;
//    private IUserWalletLogic userWalletLogic = null;
//
//    @Before
//    public void setUp(){
//        testWalletDB = new TestWalletDatabase();
//        testAccountDB = new TestAccountDatabase(testWalletDB);
//        this.userWalletLogic = new UserWalletLogic(testAccountDB,testWalletDB);
//    }
//
//    //test: get AccountError if the user searched for does not exist: get, deposit, withdraw, and get transactions
//    //TODO do this for the other methods :/
//    @Test(expected = AccountException.class)
//    public void testAccountDNEGet() throws AccountException, WalletException{
//        this.userWalletLogic.getAmount("admin");
//    }
//
//    //TODO test: get account error if there are users in the database, but we search for the wrong one?
//
//    //test: balance = 0 on creation
//    @Test
//    public void testStartBalance() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user1","admin");
//        assertEquals(0, this.userWalletLogic.getAmount("user1"));
//    }
//
//    //test: depositing -ve amounts invalid
//    @Test(expected = WalletException.class)
//    public void testNegativeDeposit() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user2","admin");
//        this.userWalletLogic.deposit("user2", -100);
//    }
//
//    //test: wallet balance increases after successful deposit
//    @Test
//    public void testGoodDeposit() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user3","admin");
//        this.userWalletLogic.deposit("user3", 100);
//        assertEquals(100, this.userWalletLogic.getAmount("user3"));
//    }
//
//    //test: withdrawing -ve amounts invalid
//    @Test(expected = WalletException.class)
//    public void testNegativeWithdraw() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user4","admin");
//        this.userWalletLogic.withdraw("user4", -100);
//    }
//
//    //test: withdrawing more than is in the account invalid
//    @Test(expected = WalletException.class)
//    public void testOverdraftWithdraw() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user5","admin");
//        this.userWalletLogic.deposit("user5", 100);
//        this.userWalletLogic.withdraw("user5", 200);
//    }
//
//    //test: wallet balance decreases after successful withdraw
//    @Test
//    public void testGoodWithdraw() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user6","admin");
//        this.userWalletLogic.deposit("user6", 100);
//        this.userWalletLogic.withdraw("user6", 73);
//        assertEquals(27,userWalletLogic.getAmount("user6"));
//    }
//
//    //test: two different users accounts are separate
//    @Test
//    public void testSeparateWallets() throws AccountException, WalletException {
//        this.testAccountDB.insertUser("u","u","user7","admin");
//        this.testAccountDB.insertUser("u","u","user8","admin");
//        this.userWalletLogic.deposit("user7", 333);
//        this.userWalletLogic.deposit("user8", 8301);
//        assertEquals(333,userWalletLogic.getAmount("user7"));
//        assertEquals(8301,userWalletLogic.getAmount("user8"));
//    }
//
//    //TODO test: number of transactions on creation is 0
//    //TODO test: number of transactions after deposits/withdraws matches the number of deposits/withdraws
//    //TODO test: transaction after a single deposit has correct information
//    //TODO test: transaction after a single withdraw has correct information
//
//    //TODO: what else?
}
