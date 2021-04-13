package com.comp3350.webudget;

import com.comp3350.webudget.business.GroupLogicTest;
import com.comp3350.webudget.business.TransactionLogicTest;
import com.comp3350.webudget.business.UserLogicTest;
import com.comp3350.webudget.business.UserWalletLogicTest;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserLogicTest.class,
        UserWalletLogicTest.class,
        GroupLogicTest.class,
        TransactionLogicTest.class,
})
public class UnitTests
{

}
