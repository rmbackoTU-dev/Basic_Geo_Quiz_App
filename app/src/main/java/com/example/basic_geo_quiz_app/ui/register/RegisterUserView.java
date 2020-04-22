package com.example.basic_geo_quiz_app.ui.register;

/**
 * Exposes the Registration variables to the user interface.
 */
public class RegisterUserView {
    public String firstName;
    public String lastName;
    public String email;

    public RegisterUserView(String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
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


}
