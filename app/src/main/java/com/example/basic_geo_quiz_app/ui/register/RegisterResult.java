package com.example.basic_geo_quiz_app.ui.register;

import androidx.annotation.Nullable;

public class RegisterResult {
    @Nullable
    private RegisterUserView success;
    @Nullable
    private Integer error;

    RegisterResult(@Nullable Integer error) {
        this.error = error;
    }

    RegisterResult(@Nullable RegisterUserView success) {
        this.success = success;
    }

    @Nullable
    public RegisterUserView getSuccess() {
        return this.success;
    }

    @Nullable
    public Integer getError() {
        return this.error;
    }
}
