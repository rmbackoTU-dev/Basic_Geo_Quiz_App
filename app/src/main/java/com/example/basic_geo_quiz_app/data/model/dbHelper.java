package com.example.basic_geo_quiz_app.data.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class dbHelper extends SQLiteOpenHelper {

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
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ACCOUNT_CREDENTIALS);
        db.execSQL(SQL_CREATE_ACCOUNT_STATISTICS);
        db.execSQL(SQL_CREATE_ACCOUNT_DETAILS);
        db.execSQL(SQL_CREATE_GAME_QUESTIONS);
        db.execSQL(SQL_CREATE_GAME_ANSWERS);
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
}
