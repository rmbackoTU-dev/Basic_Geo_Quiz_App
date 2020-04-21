package com.example.basic_geo_quiz_app.ui.register;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.basic_geo_quiz_app.R;
import com.example.basic_geo_quiz_app.data.RegistrationRepository;

import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegistrationFormState> registrationForm = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registrationResult = new MutableLiveData<>();
    private RegistrationRepository registrationRepository;

    public RegisterViewModel(RegistrationRepository registration) {
        this.registrationRepository = registration;
    }

    public LiveData<RegistrationFormState> getRegistrationForm() {
        return registrationForm;
    }

    public LiveData<RegisterResult> getRegistrationResult() {
        return registrationResult;
    }

    public void registrationDataChanged(String firstName, String lastName,
                                        String email, String password,
                                        String repeatPassword) {
        if (!isNameValid(firstName)) {
            registrationForm.setValue(new RegistrationFormState(null,
                    null, null, R.string.first_name_error,
                    null));
        }
        if (!isNameValid(lastName)) {
            registrationForm.setValue(new RegistrationFormState(null,
                    null, null, null,
                    R.string.last_name_error));

        }
        if (!isUserNameValid(email)) {
            registrationForm.setValue(new RegistrationFormState(R.string.email_error,
                    null, null, null,
                    null));
        }
        if (!isPasswordValid(password)) {
            registrationForm.setValue(new RegistrationFormState(null,
                    R.string.invalid_password, null, null,
                    null));
        }
        if (!isPasswordValid(repeatPassword)) {
            registrationForm.setValue(new RegistrationFormState(null,
                    null, R.string.empty_password_error, null,
                    null));
        }
    }

    private void registerUser() {

    }

    // email validation check
    private boolean isUserNameValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    public boolean isNameValid(String name) {
        boolean validName = false;
        if (name != null) {
            Pattern onlyLetters = Pattern.compile("[a-zA-Z]");
            validName = onlyLetters.matcher(name).matches();
        }
        return validName;
    }

    // password validation check
    private boolean isPasswordValid(String password) {
        String trimedPassword = password.trim();
        boolean lengthCheck = trimedPassword.length() > 8;
        String[] specialChars = {"~", "!", "#", "$", "^", "&", "*", "?", "."};
        String decimalRegex = "\\d";
        String lowerRegex = "[a-z]";
        String upperRegex = "[A-Z]";
        Pattern digits = Pattern.compile(decimalRegex);
        Pattern lower = Pattern.compile(lowerRegex);
        Pattern upper = Pattern.compile(upperRegex);
        boolean digitCheck = digits.matcher(trimedPassword).matches();
        boolean lowerCheck = lower.matcher(trimedPassword).matches();
        boolean uppperCheck = upper.matcher(trimedPassword).matches();
        boolean specialCharCheck = false;
        int i = 0;
        while (((i < specialChars.length) || (specialCharCheck == false))) {
            specialCharCheck = trimedPassword.contains(specialChars[i]);
            i = i + 1;
        }
        return password != null && lengthCheck && uppperCheck && lowerCheck && specialCharCheck
                && digitCheck;
    }



}
