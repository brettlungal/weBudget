package com.comp3350.webudget;

import com.comp3350.webudget.business.UserLogicTest;
import com.comp3350.webudget.business.UserWalletLogicTest;
import com.comp3350.webudget.objects.Account_test;
import com.comp3350.webudget.objects.Group_test;
import com.comp3350.webudget.objects.Wallet_test;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        Account_test.class,
        Group_test.class,
        UserLogicTest.class,
        UserWalletLogicTest.class,
})
public class AllTests
{

}
