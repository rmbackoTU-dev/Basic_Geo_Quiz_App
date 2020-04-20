package com.example.basic_geo_quiz_app.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private int correctAnswers;
    private int incorrectAnswers;

    LoggedInUserView(String displayName, int correct, int incorrect) {
        this.displayName = displayName;
        this.correctAnswers = correct;
        this.incorrectAnswers = incorrect;

    }

    protected String getDisplayName() {
        return displayName;
    }

    protected int getCorrectAnswers() {
        return this.correctAnswers;
    }

    protected int getIncorrectAnswers() {
        return this.incorrectAnswers;
    }

}

