package com.comp3350.webudget;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.comp3350.webudget.business.AccessCoursesIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCoursesIT.class
})
public class IntegrationTests {
}
