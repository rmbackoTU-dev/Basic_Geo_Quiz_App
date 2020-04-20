package com.example.basic_geo_quiz_app.data.model;

import android.provider.BaseColumns;

public final class accountTables {

    private accountTables()
    {

    }

    public static final class accountCredentials implements BaseColumns
    {
        public static final String TABLE_NAME="account_credentials";
        public static final String COLUMN_ONE_NAME="email";
        public static final String COLUMN_TWO_NAME="password_hash";
        public static final String COLUMN_THREE_NAME="account_id_pk";
        public static final String COLUMN_ONE_TYPE="TEXT";
        public static final String COLUMN_TWO_TYPE="INTEGER";
        public static final String COLUMN_THREE_TYPE="INTEGER PRIMARY KEY ASC";
    }

    public static final class accountDetails implements BaseColumns
    {
        public static final String TABLE_NAME="account_details";
        public static final String COLUMN_ONE_NAME="first_name";
        public static final String COLUMN_TWO_NAME="last_name";
        public static final String COLUMN_THREE_NAME="account_id_fk";
        public static final String COLUMN_FOUR_NAME="stat_id_fk";
        public static final String COLUMN_FIVE_NAME="account_details_id_pk";
        public static final String COLUMN_ONE_TYPE="TEXT";
        public static final String COLUMN_TWO_TYPE="TEXT";
        public static final String COLUMN_THREE_TYPE="INTEGER UNIQUE";
        public static final String COLUMN_FOUR_TYPE="INTEGER UNIQUE";
        public static final String COLUMN_FIVE_TYPE="INTEGER PRIMARY KEY ASC";
    }

    public static final class accountStatistics implements BaseColumns
    {
        public static final String TABLE_NAME="account_statistics";
        public static final String COLUMN_ONE_NAME="stat_id";
        public static final String COLUMN_TWO_NAME="correct_answers";
        public static final String COLUMN_THREE_NAME="incorrect_answers";
        public static final String COLUMN_ONE_TYPE="INTEGER PRIMARY KEY ASC";
        public static final String COLUMN_TWO_TYPE="INTEGER";
        public static final String COLUMN_THREE_TYPE="INTEGER";
    }

}
