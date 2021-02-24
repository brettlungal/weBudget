package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.IAccountDatabase;
import com.comp3350.webudget.persistence.AccountDatabase;

public class Services {
    private static IAccountDatabase accountPersistence = null;

    public static synchronized IAccountDatabase accountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new AccountDatabase();
        }
        return accountPersistence;
    }


}
