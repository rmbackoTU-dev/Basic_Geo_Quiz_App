package com.example.basic_geo_quiz_app.data;

import com.example.basic_geo_quiz_app.data.model.userRecord;

/**
 * Adds, removes, and changes account data in the SQLlite database
 */
public class RegistrationRepository {

    private static RegistrationRepository instance;

    private RegistrationDataSource dataSource;
    private userRecord record;

    public RegistrationRepository(RegistrationDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static RegistrationRepository getInstance(RegistrationDataSource dataSource) {
        if (instance == null) {
            instance = new RegistrationRepository(dataSource);
        }
        return instance;
    }

    public boolean isRegistered() {
        return record != null;
    }

    public void register(String firstName, String lastName, String email,
                         String password, String repeatPassword) {
        
    }

}
