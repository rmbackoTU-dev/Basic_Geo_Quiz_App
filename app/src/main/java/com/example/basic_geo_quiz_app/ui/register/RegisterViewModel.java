package com.example.basic_geo_quiz_app.ui.register;

import android.net.wifi.WifiConfiguration;
import android.util.Patterns;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.basic_geo_quiz_app.R;
import com.example.basic_geo_quiz_app.data.RegistrationRepository;
import com.example.basic_geo_quiz_app.data.model.userRecord;

import java.util.regex.Pattern;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegistrationFormState> registrationFormMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registrationResultMutableLiveData = new MutableLiveData<>();
    private RegistrationRepository registrationRepository;

    public RegisterViewModel(RegistrationRepository registration) {
        this.registrationRepository = registration;
    }

    public void registrationDataChanged(String firstName, String lastName,
                                        String email, String password,
                                        String repeatPassword) {
        if (!isNameValid(firstName)) {
            registrationFormMutableLiveData.setValue(new RegistrationFormState(null,
                    null, null, R.string.first_name_error,
                    null));
        }
        if (!isNameValid(lastName)) {
            registrationFormMutableLiveData.setValue(new RegistrationFormState(null,
                    null, null, null,
                    R.string.last_name_error));

        }
        if (!isUserNameValid(email)) {
            registrationFormMutableLiveData.setValue(new RegistrationFormState(R.string.email_error,
                    null, null, null,
                    null));
        }
        if (!isPasswordValid(password)) {
            registrationFormMutableLiveData.setValue(new RegistrationFormState(null,
                    R.string.invalid_password, null, null,
                    null));
        }
        if (!isPasswordValid(repeatPassword)) {
            registrationFormMutableLiveData.setValue(new RegistrationFormState(null,
                    null, R.string.empty_password_error, null,
                    null));
        }
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
