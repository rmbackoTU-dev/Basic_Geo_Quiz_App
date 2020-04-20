package com.example.basic_geo_quiz_app.data.model;

/**
 * Data class that captures user information for logged in users retrieved from SQLite database
 * combines information
 *
 */
public class LoggedInUser {

    private String firstName;
    private String lastName;
    private String email;
    private int correctAnswers;
    private int incorrectAnswers;
    private boolean loggedIn;

    public LoggedInUser(String email, String firstName, String lastName,
                        int correct, int incorrect) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.correctAnswers = correct;
        this.incorrectAnswers = incorrect;
        this.loggedIn = false;
    }

    @Override
    public String toString() {
        return this.getDisplayName() + " | " +
                this.getEmail() + " | correct: " + this.getCorrectAnswers() +
                " | incorrect: " + this.getIncorrectAnswers();
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return firstName + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }
}
