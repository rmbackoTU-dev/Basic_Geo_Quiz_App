package com.example.basic_geo_quiz_app.data.model;

import android.provider.BaseColumns;

public class userRecord {

    /**
     * private constructor to encourage use of individual constructors
     */
    private userRecord() {
    }

    @Override
    public String toString() {
        String recordString = "";
        if (this instanceof accountCredentials) {
            userRecord.accountCredentials credentialRecord = (accountCredentials) this;
            recordString = credentialRecord.getEmail() + ", " +
                    credentialRecord.getPasswordHash() + ", " +
                    credentialRecord.account_id_pk;
            //email not added here because compare hash function should be called
        } else if (this instanceof accountDetails) {
            userRecord.accountDetails detailsRecord = (accountDetails) this;
            recordString = detailsRecord.getFirstName() + ", " +
                    detailsRecord.getLastName() + ", " +
                    detailsRecord.getAccount_id_fk() + ", " +
                    detailsRecord.getStat_id_fk() + ", " +
                    detailsRecord.getAccount_details_id_pk();

        } else if (this instanceof accountStatistics) {
            userRecord.accountStatistics statisticsRecord = (accountStatistics) this;
            recordString = statisticsRecord.getStat_id() + ", " +
                    statisticsRecord.getCorrect_answers() + ", " +
                    statisticsRecord.getIncorrect_answers();
        } else if (this instanceof errorRecord) {
            userRecord.errorRecord thisErrorRecord = (errorRecord) this;
            Exception currentError = thisErrorRecord.getError();
            recordString = currentError.toString();
        }
        return recordString;
    }

    public static class accountCredentials extends userRecord implements BaseColumns
    {
        public static final String TABLE_NAME="account_credentials";
        public static final String COLUMN_ONE_NAME="email";
        public static final String COLUMN_TWO_NAME="password_hash";
        public static final String COLUMN_THREE_NAME="account_id_pk";
        public static final String COLUMN_FOUR_NAME = "password_salt";
        public static final String COLUMN_ONE_TYPE="TEXT";
        public static final String COLUMN_TWO_TYPE = "TEXT";
        public static final String COLUMN_THREE_TYPE="INTEGER PRIMARY KEY ASC";
        public static final String COLUMN_FOUR_TYPE = "BLOB";

        private String email;
        private String passwordHash;
        private int account_id_pk;
        private byte[] passwordSalt;

        public accountCredentials(String emailAddress, String pwHash, byte[] salt, int acc_id) {
            this.email = emailAddress;
            this.passwordHash = pwHash;
            this.account_id_pk = acc_id;
            this.passwordSalt = salt;
        }

        public String getEmail() {
            return email;
        }

        public String getPasswordHash() {
            return passwordHash;
        }

        public int getAccount_id_pk() {
            return account_id_pk;
        }

        public byte[] getPasswordSalt() {
            return passwordSalt;
        }
    }

    public static class accountDetails extends userRecord implements BaseColumns
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

        private String firstName;
        private String lastName;
        private int account_id_fk;
        private int stat_id_fk;
        private int account_details_id_pk;

        public accountDetails(String fN, String lN, int acc_id,
                              int stat_id, int acc_det_id) {
            this.firstName = fN;
            this.lastName = lN;
            this.account_id_fk = acc_id;
            this.stat_id_fk = stat_id;
            this.account_details_id_pk = acc_det_id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAccount_id_fk() {
            return account_id_fk;
        }

        public int getStat_id_fk() {
            return stat_id_fk;
        }

        public int getAccount_details_id_pk() {
            return account_details_id_pk;
        }
    }

    public static class accountStatistics extends userRecord implements BaseColumns {
        public static final String TABLE_NAME="account_statistics";
        public static final String COLUMN_ONE_NAME="stat_id";
        public static final String COLUMN_TWO_NAME="correct_answers";
        public static final String COLUMN_THREE_NAME="incorrect_answers";
        public static final String COLUMN_ONE_TYPE="INTEGER PRIMARY KEY ASC";
        public static final String COLUMN_TWO_TYPE="INTEGER";
        public static final String COLUMN_THREE_TYPE="INTEGER";


        private int stat_id;
        private int correct_answers;
        private int incorrect_answers;

        public accountStatistics(int id, int correct, int incorrect) {
            this.stat_id = id;
            this.correct_answers = correct;
            this.incorrect_answers = incorrect;
        }

        public int getStat_id() {
            return stat_id;
        }

        public int getCorrect_answers() {
            return correct_answers;
        }

        public int getIncorrect_answers() {
            return incorrect_answers;
        }
    }

    public static class errorRecord extends userRecord {
        private Exception error;

        public errorRecord(Exception e) {
            this.error = e;
        }

        public Exception getError() {
            return error;
        }
    }

}
