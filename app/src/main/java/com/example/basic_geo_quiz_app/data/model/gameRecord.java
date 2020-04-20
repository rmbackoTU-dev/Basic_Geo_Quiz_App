package com.example.basic_geo_quiz_app.data.model;

import android.provider.BaseColumns;

public class gameRecord {

    private gameRecord()
    { }

    @Override
    public String toString() {
        String recordString = "";
        if (this instanceof gameQuestions) {
            gameRecord.gameQuestions questionRecord = (gameQuestions) this;
            recordString = questionRecord.getQuestions_id() + ", " +
                    questionRecord.getQuestion();
        } else if (this instanceof gameAnswers) {
            gameRecord.gameAnswers answerRecord = (gameAnswers) this;
            recordString = answerRecord.isCorrect() + ", " +
                    answerRecord.getAnswer() + ", " +
                    answerRecord.getQuestion_id_fk() + ", " +
                    answerRecord.getAnswer_id();
        } else if (this instanceof errorRecord) {
            gameRecord.errorRecord errRecord = (errorRecord) this;
            Exception gameRecError = errRecord.getError();
            recordString = gameRecError.toString();
        }
        return recordString;
    }

    public static final class gameQuestions extends gameRecord implements BaseColumns
    {
        public static final String TABLE_NAME="game_questions";
        public static final String COLUMN_ONE_NAME="question";
        public static final String COLUMN_TWO_NAME="question_id";
        public static final String COLUMN_ONE_TYPE="TEXT";
        public static final String COLUMN_TWO_TYPE="INTEGER PRIMARY KEY";

        private int questions_id;
        private String question;

        public gameQuestions(int id, String question) {
            this.questions_id = id;
            this.question = question;
        }

        public int getQuestions_id() {
            return questions_id;
        }

        public String getQuestion() {
            return question;
        }
    }

    public static final class gameAnswers extends gameRecord implements BaseColumns
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

        private boolean correct;
        private String answer;
        private int question_id_fk;
        private int answer_id;

        public gameAnswers(boolean correct, String answ, int question_id,
                           int id) {
            this.correct = correct;
            this.answer = answ;
            this.question_id_fk = question_id;
            this.answer_id = id;
        }

        public boolean isCorrect() {
            return this.correct;
        }

        public String getAnswer() {
            return this.answer;
        }

        public int getQuestion_id_fk() {
            return this.question_id_fk;
        }

        public int getAnswer_id() {
            return this.answer_id;
        }
    }

    public static final class errorRecord extends gameRecord {
        private Exception error;

        public errorRecord(Exception e) {
            this.error = e;
        }

        public Exception getError() {
            return error;
        }
    }
}
