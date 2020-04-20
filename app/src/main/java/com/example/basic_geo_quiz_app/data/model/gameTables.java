package com.example.basic_geo_quiz_app.data.model;

public class gameTables {

    private gameTables()
    { }

    public static final class gameQuestions
    {
        public static final String TABLE_NAME="game_questions";
        public static final String COLUMN_ONE_NAME="question";
        public static final String COLUMN_TWO_NAME="question_id";
        public static final String COLUMN_ONE_TYPE="TEXT";
        public static final String COLUMN_TWO_TYPE="INTEGER PRIMARY KEY";
    }

    public static final class gameAnswers
    {
        public static final String TABLE_NAME="game_answers";
        public static final String COLUMN_ONE_NAME="correct";
        public static final String COLUMN_TWO_NAME="answer";
        public static final String COLUMN_THREE_NAME="question_id_fk";
        public static final String COLUMN_FOUR_NAME="answer_id";
        public static final String COLUMN_ONE_TYPE="NUMERIC";
        public static final String COLUMN_TWO_TYPE="TEXT";
        public static final String COLUMN_THREE_TYPE="INTEGER UNIQUE";
        public static final String COLUMN_FOUR_TYPE="INTEGER PRIMARY KEY";
    }
}
