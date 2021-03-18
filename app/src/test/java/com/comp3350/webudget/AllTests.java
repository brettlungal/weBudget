package com.comp3350.webudget;

import com.comp3350.webudget.business.GroupLogicTest;
import com.comp3350.webudget.business.UserLogicTest;
import com.comp3350.webudget.business.UserWalletLogicTest;
import com.comp3350.webudget.objects.Account_test;
import com.comp3350.webudget.objects.Group_test;
import com.comp3350.webudget.objects.Wallet_test;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserLogicTest.class,
        UserWalletLogicTest.class,
        //GroupLogicTest.class,
})
public class AllTests
{

}
