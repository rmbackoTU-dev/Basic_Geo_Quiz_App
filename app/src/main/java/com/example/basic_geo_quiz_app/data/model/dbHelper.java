package com.example.basic_geo_quiz_app.data.model;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    public Context currentContext;
    public static final int  DB_VERSION=1;
    public static final String DatabaseName="QuizAppDatabase.db";
    public static final String SQL_CREATE_ACCOUNT_CREDENTIALS=
            "CREATE TABLE " + userRecord.accountCredentials.TABLE_NAME + " (" +
                    userRecord.accountCredentials.COLUMN_ONE_NAME +
                    " " + userRecord.accountCredentials.COLUMN_ONE_TYPE +
                    ", " + userRecord.accountCredentials.COLUMN_TWO_NAME +
                    " " + userRecord.accountCredentials.COLUMN_TWO_TYPE +
                    ", " + userRecord.accountCredentials.COLUMN_THREE_NAME +
                    " " + userRecord.accountCredentials.COLUMN_THREE_TYPE +
                    ");";

    public static final String SQL_CREATE_ACCOUNT_DETAILS=
            "CREATE TABLE " + userRecord.accountDetails.TABLE_NAME + " (" +
                    userRecord.accountDetails.COLUMN_ONE_NAME +
                    " " + userRecord.accountDetails.COLUMN_ONE_TYPE +
                    ", " + userRecord.accountDetails.COLUMN_TWO_NAME +
                    " " + userRecord.accountDetails.COLUMN_TWO_TYPE +
                    ", " + userRecord.accountDetails.COLUMN_THREE_NAME +
                    " " + userRecord.accountDetails.COLUMN_THREE_TYPE +
                    ", " + userRecord.accountDetails.COLUMN_FOUR_NAME +
                    " " + userRecord.accountDetails.COLUMN_FOUR_TYPE +
                    ", " + userRecord.accountDetails.COLUMN_FIVE_NAME +
                    " " + userRecord.accountDetails.COLUMN_FIVE_TYPE +
                    ", " + "FOREIGN KEY(" + userRecord.accountDetails.COLUMN_THREE_NAME +
                    ") REFERENCES  " + userRecord.accountCredentials.TABLE_NAME + "(" +
                    userRecord.accountCredentials.COLUMN_THREE_NAME + ")" +
                    ", " + "FOREIGN KEY(" + userRecord.accountDetails.COLUMN_FOUR_NAME +
                    ") REFERENCES " + userRecord.accountStatistics.TABLE_NAME + "(" +
                    userRecord.accountStatistics.COLUMN_ONE_NAME + "));";

    public static final String SQL_CREATE_ACCOUNT_STATISTICS=
            "CREATE TABLE " + userRecord.accountStatistics.TABLE_NAME + " (" +
                    userRecord.accountStatistics.COLUMN_ONE_NAME +
                    " " + userRecord.accountStatistics.COLUMN_ONE_TYPE +
                    ", " + userRecord.accountStatistics.COLUMN_TWO_NAME +
                    " " + userRecord.accountStatistics.COLUMN_TWO_TYPE +
                    ", " + userRecord.accountStatistics.COLUMN_THREE_NAME +
                    " " + userRecord.accountStatistics.COLUMN_THREE_TYPE +
                    ");";

    public static final String SQL_CREATE_GAME_QUESTIONS=
            "CREATE TABLE " + gameRecord.gameQuestions.TABLE_NAME + " (" +
                    " " + gameRecord.gameQuestions.COLUMN_ONE_NAME +
                    " " + gameRecord.gameQuestions.COLUMN_ONE_TYPE +
                    ", " + gameRecord.gameQuestions.COLUMN_TWO_NAME +
                    " " + gameRecord.gameQuestions.COLUMN_TWO_TYPE +
            ");";

    public static final String SQL_CREATE_GAME_ANSWERS=
            "CREATE TABLE " + gameRecord.gameAnswers.TABLE_NAME + " (" +
                    " " + gameRecord.gameAnswers.COLUMN_ONE_NAME +
                    " " + gameRecord.gameAnswers.COLUMN_ONE_TYPE +
                    ", " + gameRecord.gameAnswers.COLUMN_TWO_NAME +
                    " " + gameRecord.gameAnswers.COLUMN_TWO_TYPE +
                    ", " + gameRecord.gameAnswers.COLUMN_THREE_NAME +
                    " " + gameRecord.gameAnswers.COLUMN_THREE_TYPE +
                    ", " + gameRecord.gameAnswers.COLUMN_FOUR_NAME +
                    " " + gameRecord.gameAnswers.COLUMN_FOUR_TYPE +
                    ", " + "FOREIGN KEY(" + gameRecord.gameAnswers.COLUMN_THREE_NAME + ")" +
                    " REFERENCES " + gameRecord.gameQuestions.TABLE_NAME + "(" +
                    gameRecord.gameQuestions.COLUMN_TWO_NAME + "));";


    public dbHelper(Context context)
    {
        super(context, DatabaseName, null, DB_VERSION);
        this.currentContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ACCOUNT_CREDENTIALS);
        db.execSQL(SQL_CREATE_ACCOUNT_STATISTICS);
        db.execSQL(SQL_CREATE_ACCOUNT_DETAILS);
        db.execSQL(SQL_CREATE_GAME_QUESTIONS);
        db.execSQL(SQL_CREATE_GAME_ANSWERS);

        SQLiteDatabase writableDB = getWritableDatabase();
        ContentValues data = new ContentValues();
        long newRowId;
        try {
            AssetManager am = this.currentContext.getAssets();
            InputStream gameQuestionsIS = am.open("game_questions.csv");
            List<String[]> gameQuestionList = readCSV(gameQuestionsIS);
            gameQuestionsIS.close();
            InputStream gameAnswersIS = am.open("game_answers.csv");
            List<String[]> gameAnswerList = readCSV(gameAnswersIS);
            gameAnswersIS.close();

            for (String[] question_field : gameQuestionList) {
                data.put(gameRecord.gameQuestions.COLUMN_TWO_NAME, question_field[0]);
                data.put(gameRecord.gameQuestions.COLUMN_ONE_NAME, question_field[1]);
                newRowId = writableDB.insert(gameRecord.gameQuestions.TABLE_NAME,
                        null, data);
                data.clear();
            }

            for (String[] answer_field : gameAnswerList) {
                data.put(gameRecord.gameAnswers.COLUMN_FOUR_NAME, answer_field[0]);
                data.put(gameRecord.gameAnswers.COLUMN_THREE_NAME, answer_field[1]);
                data.put(gameRecord.gameAnswers.COLUMN_TWO_NAME, answer_field[2]);
                data.put(gameRecord.gameAnswers.COLUMN_ONE_NAME, answer_field[3]);
                newRowId = writableDB.insert(gameRecord.gameQuestions.TABLE_NAME,
                        null, data);
                data.clear();
            }


        } catch (IOException ioe) {
            System.err.println("Error while opening file:");
            ioe.printStackTrace();
        }

    }


    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion,
                           int newVersion)
    {

    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {

    }

    private static List readCSV(InputStream csvStream) {
        List resultList = new ArrayList();
        try {
            BufferedReader fileBuffReader = new BufferedReader(
                    new InputStreamReader((csvStream)));
            String readLine;
            while ((readLine = fileBuffReader.readLine()) != null) {
                String[] row = readLine.split(",");
                resultList.add(row);
            }
            fileBuffReader.close();

        } catch (Exception e) {
            System.err.println("An error occured while parseing the csv");
            e.printStackTrace();
        } finally {
            return resultList;
        }


    }
}
