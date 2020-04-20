package com.example.basic_geo_quiz_app.data.model;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
            "CREATE TABLE "+ accountTables.accountCredentials.TABLE_NAME+" ("+
                    accountTables.accountCredentials.COLUMN_ONE_NAME+
                    " "+ accountTables.accountCredentials.COLUMN_ONE_TYPE+
                    ", "+ accountTables.accountCredentials.COLUMN_TWO_NAME+
                    " "+ accountTables.accountCredentials.COLUMN_TWO_TYPE+
                    ", "+ accountTables.accountCredentials.COLUMN_THREE_NAME+
                    " "+ accountTables.accountCredentials.COLUMN_THREE_TYPE+
                    ");";

    public static final String SQL_CREATE_ACCOUNT_DETAILS=
            "CREATE TABLE "+ accountTables.accountDetails.TABLE_NAME+" ("+
                    accountTables.accountDetails.COLUMN_ONE_NAME+
                    " "+ accountTables.accountDetails.COLUMN_ONE_TYPE+
                    ", "+ accountTables.accountDetails.COLUMN_TWO_NAME+
                    " "+ accountTables.accountDetails.COLUMN_TWO_TYPE+
                    ", "+ accountTables.accountDetails.COLUMN_THREE_NAME+
                    " "+ accountTables.accountDetails.COLUMN_THREE_TYPE+
                    ", "+ accountTables.accountDetails.COLUMN_FOUR_NAME+
                    " "+ accountTables.accountDetails.COLUMN_FOUR_TYPE+
                    ", "+ accountTables.accountDetails.COLUMN_FIVE_NAME+
                    " "+ accountTables.accountDetails.COLUMN_FIVE_TYPE+
                    ", "+"FOREIGN KEY("+accountTables.accountDetails.COLUMN_THREE_NAME+
                    ") REFERENCES  "+accountTables.accountCredentials.TABLE_NAME+"("+
                     accountTables.accountCredentials.COLUMN_THREE_NAME+")"+
                    ", "+"FOREIGN KEY("+accountTables.accountDetails.COLUMN_FOUR_NAME+
                    ") REFERENCES "+accountTables.accountStatistics.TABLE_NAME+"("+
                    accountTables.accountStatistics.COLUMN_ONE_NAME+"));";

    public static final String SQL_CREATE_ACCOUNT_STATISTICS=
            "CREATE TABLE "+ accountTables.accountStatistics.TABLE_NAME+" ("+
                    accountTables.accountStatistics.COLUMN_ONE_NAME+
                    " "+ accountTables.accountStatistics.COLUMN_ONE_TYPE+
                    ", "+ accountTables.accountStatistics.COLUMN_TWO_NAME+
                    " "+ accountTables.accountStatistics.COLUMN_TWO_TYPE+
                    ", "+ accountTables.accountStatistics.COLUMN_THREE_NAME+
                    " "+ accountTables.accountStatistics.COLUMN_THREE_TYPE+
                    ");";

    public static final String SQL_CREATE_GAME_QUESTIONS=
            "CREATE TABLE "+ gameTables.gameQuestions.TABLE_NAME+" ("+
            " "+ gameTables.gameQuestions.COLUMN_ONE_NAME+
            " "+ gameTables.gameQuestions.COLUMN_ONE_TYPE+
            ", "+ gameTables.gameQuestions.COLUMN_TWO_NAME+
            " "+ gameTables.gameQuestions.COLUMN_TWO_TYPE+
            ");";

    public static final String SQL_CREATE_GAME_ANSWERS=
            "CREATE TABLE "+ gameTables.gameAnswers.TABLE_NAME+" ("+
            " "+ gameTables.gameAnswers.COLUMN_ONE_NAME+
            " "+ gameTables.gameAnswers.COLUMN_ONE_TYPE+
            ", "+ gameTables.gameAnswers.COLUMN_TWO_NAME+
            " "+ gameTables.gameAnswers.COLUMN_TWO_TYPE+
            ", "+ gameTables.gameAnswers.COLUMN_THREE_NAME+
            " "+ gameTables.gameAnswers.COLUMN_THREE_TYPE+
            ", "+ gameTables.gameAnswers.COLUMN_FOUR_NAME+
            " "+gameTables.gameAnswers.COLUMN_FOUR_TYPE+
            ", "+"FOREIGN KEY("+gameTables.gameAnswers.COLUMN_THREE_NAME+")"+
                   " REFERENCES "+gameTables.gameQuestions.TABLE_NAME+"("+
                    gameTables.gameQuestions.COLUMN_TWO_NAME+"));";


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
                data.put(gameTables.gameQuestions.COLUMN_TWO_NAME, question_field[0]);
                data.put(gameTables.gameQuestions.COLUMN_ONE_NAME, question_field[1]);
                newRowId = writableDB.insert(gameTables.gameQuestions.TABLE_NAME,
                        null, data);
                data.clear();
            }

            for (String[] answer_field : gameAnswerList) {
                //todo: add answer csv fields to database
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
