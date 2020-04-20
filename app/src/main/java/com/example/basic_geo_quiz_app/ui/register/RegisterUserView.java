package com.example.basic_geo_quiz_app.ui.register;

/**
 * Exposes the Registration variables to the user interface.
 */
public class RegisterUserView {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String repeat_password;

    public RegisterUserView(String first, String last, String email, String password,
                            String repeat_password) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
        this.password = password;
        this.repeat_password = repeat_password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeat_password() {
        return repeat_password;
    }
}
