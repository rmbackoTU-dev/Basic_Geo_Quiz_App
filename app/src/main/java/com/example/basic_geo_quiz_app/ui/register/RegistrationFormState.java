package com.example.basic_geo_quiz_app.ui.register;

import androidx.annotation.Nullable;

/**
 * Validates form data for registration activity
 */
public class RegistrationFormState {
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer repeatPasswordError;
    @Nullable
    private Integer firstNameError;
    @Nullable
    private Integer lastNameError;
    private boolean dataVaild;


    public RegistrationFormState(@Nullable Integer emailError,
                                 @Nullable Integer passwordError,
                                 @Nullable Integer repeatPasswordError,
                                 @Nullable Integer firstNameError,
                                 @Nullable Integer lastNameError) {
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
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getRepeatPasswordError() {
        return repeatPasswordError;
    }

    @Nullable
    public Integer getFirstNameError() {
        return firstNameError;
    }

    @Nullable
    public Integer getLastNameError() {
        return lastNameError;
    }

    public boolean isDataValid() {
        return dataVaild;
    }
}
