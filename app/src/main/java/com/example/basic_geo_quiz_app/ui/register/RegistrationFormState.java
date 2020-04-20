package com.example.basic_geo_quiz_app.ui.register;

import androidx.annotation.Nullable;

/**
 * Validates form data for registration activity
 */
public class RegistrationFormState {
    @Nullable
    private String emailError;
    @Nullable
    private String passwordError;
    @Nullable
    private String repeatPasswordError;
    @Nullable
    private String firstNameError;
    @Nullable
    private String lastNameError;
    private boolean dataVaild;


    public RegistrationFormState(@Nullable String emailError,
                                 @Nullable String passwordError,
                                 @Nullable String repeatPasswordError,
                                 @Nullable String firstNameError,
                                 @Nullable String lastNameError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.repeatPasswordError = repeatPasswordError;
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.dataVaild = false;
    }

    public RegistrationFormState(boolean vaild) {
        this.emailError = null;
        this.passwordError = null;
        this.repeatPasswordError = null;
        this.firstNameError = null;
        this.lastNameError = null;
        this.dataVaild = vaild;

    }

    @Nullable
    public String getEmailError() {
        return emailError;
    }

    @Nullable
    public String getPasswordError() {
        return passwordError;
    }

    @Nullable
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    @Nullable
    public String getFirstNameError() {
        return firstNameError;
    }

    @Nullable
    public String getLastNameError() {
        return lastNameError;
    }

    public boolean isDataValid() {
        return dataVaild;
    }
}
