package com.comp3350.webudget;

import com.comp3350.webudget.business.Login_test;
import com.comp3350.webudget.business.SignupLogicTest;
import com.comp3350.webudget.business.UserWalletLogicTest;
import com.comp3350.webudget.objects.Account_test;
import com.comp3350.webudget.objects.Group_test;
import com.comp3350.webudget.objects.Wallet_test;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login_test.class,
        Account_test.class,
        Group_test.class,
        SignupLogicTest.class,
        UserWalletLogicTest.class,
})
public class AllTests
{

}
