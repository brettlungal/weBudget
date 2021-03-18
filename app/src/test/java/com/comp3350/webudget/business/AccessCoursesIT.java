package com.comp3350.webudget.business;

import com.comp3350.webudget.application.SignupException;
import com.comp3350.webudget.persistence.hsqldb.AccountDatabase;
import com.comp3350.webudget.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AccessCoursesIT {
    private AccountDatabase persistence;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        persistence = new AccountDatabase(this.tempDB.getAbsolutePath().replace(".script", ""));
    }

    @Test
    public void signUpSuccess() throws SignupException {
        persistence.insertUser("xuan","ge","tands","111");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }


}
