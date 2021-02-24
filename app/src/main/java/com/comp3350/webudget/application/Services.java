package com.comp3350.webudget.application;

import com.comp3350.webudget.persistence.DatabaseInterface;
import com.comp3350.webudget.persistence.accountDatabase;

public class Services {
    private static DatabaseInterface accountPersistence = null;

    public static synchronized DatabaseInterface accountPersistence() {
        if (accountPersistence == null) {
            accountPersistence = new accountDatabase();
        }
        return accountPersistence;
    }


}
