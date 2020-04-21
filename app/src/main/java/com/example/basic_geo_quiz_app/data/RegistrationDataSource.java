package com.example.basic_geo_quiz_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.basic_geo_quiz_app.data.model.dbHelper;
import com.example.basic_geo_quiz_app.data.model.userRecord;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.MessageDigest;

/**
 * Controls validations of registration data
 */
public class RegistrationDataSource {

    dbHelper databaseHelper;

    public RegistrationDataSource(Context context) {
        this.databaseHelper = new dbHelper(context);
    }

    public userRecord[] createAccount(String firstName, String lastName, String email,
                                      String password, String repeatPassword) {

        userRecord[] thisUser = {null, null, null};
        try {
            //read the database to determine what the next id for the new userRecord is
            SQLiteDatabase readableDatabase = this.databaseHelper.getReadableDatabase();
            Integer credId = null;
            Integer statId = null;
            Integer detailId = null;
            String[] credentialProjection =
                    {
                            userRecord.accountCredentials.COLUMN_THREE_NAME
                    };
            String[] statisticProjection =
                    {
                            userRecord.accountStatistics.COLUMN_ONE_NAME
                    };
            String[] detailProjection =
                    {
                            userRecord.accountDetails.COLUMN_FIVE_NAME
                    };

            Cursor cursorOne = readableDatabase.query(userRecord.accountCredentials.TABLE_NAME,
                    credentialProjection, "*", null, null,
                    null, "ORDER BY " + userRecord.accountCredentials.COLUMN_THREE_NAME + " DESC",
                    "LIMIT 1");

            if (cursorOne.getCount() == 0) {
                credId = 0;
            } else if (cursorOne.getCount() > 1) {
                //Should throw an exception here
                System.err.println("More data retrieved than expected");
            } else {
                credId = cursorOne.getInt(0);
            }

            cursorOne.close();

            Cursor cursorTwo = readableDatabase.query(userRecord.accountStatistics.TABLE_NAME,
                    statisticProjection, "*", null, null,
                    null, "ORDER BY " + userRecord.accountStatistics.COLUMN_ONE_NAME + " DESC",
                    "LIMIT 1");

            if (cursorTwo.getCount() == 0) {
                credId = 0;
            } else if (cursorTwo.getCount() > 1) {
                //Should throw an exception here
                System.err.println("More data retrieved than expected");
            } else {
                credId = cursorTwo.getInt(0);
            }

            cursorTwo.close();

            Cursor cursorThree = readableDatabase.query(userRecord.accountStatistics.TABLE_NAME,
                    statisticProjection, "*", null, null,
                    null, "ORDER BY " + userRecord.accountDetails.COLUMN_FIVE_NAME + " DESC",
                    "LIMIT 1");

            if (cursorThree.getCount() == 0) {
                credId = 0;
            } else if (cursorThree.getCount() > 1) {
                //Should throw an exception here
                System.err.println("More data retrieved than expected");
            } else {
                credId = cursorThree.getInt(0);
            }

            cursorThree.close();

            byte[] passSalt = generateSalt();
            String hashedPassword = createHashedPassword(password, passSalt);
            userRecord.accountCredentials newUserCredential =
                    new userRecord.accountCredentials(email, hashedPassword, passSalt, credId);
            //set the statistics at the start since this is a new account
            userRecord.accountStatistics newStatistics =
                    new userRecord.accountStatistics(statId, 0, 0);
            userRecord.accountDetails newUserDetails =
                    new userRecord.accountDetails(firstName, lastName,
                            credId, statId, detailId);

            thisUser = new userRecord[]{newUserCredential, newStatistics, newUserDetails};

            SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
            ContentValues credentialValues = new ContentValues();
            ContentValues statisticsValues = new ContentValues();
            ContentValues detailValues = new ContentValues();

            credentialValues.put(userRecord.accountCredentials.COLUMN_ONE_NAME,
                    newUserCredential.getEmail());
            credentialValues.put(userRecord.accountCredentials.COLUMN_TWO_NAME,
                    newUserCredential.getPasswordHash());
            credentialValues.put(userRecord.accountCredentials.COLUMN_THREE_NAME,
                    newUserCredential.getAccount_id_pk());
            credentialValues.put(userRecord.accountCredentials.COLUMN_FOUR_NAME,
                    newUserCredential.getPasswordHash());

            statisticsValues.put(userRecord.accountStatistics.COLUMN_ONE_NAME,
                    newStatistics.getStat_id());
            statisticsValues.put(userRecord.accountStatistics.COLUMN_TWO_NAME,
                    newStatistics.getCorrect_answers());
            statisticsValues.put(userRecord.accountStatistics.COLUMN_THREE_NAME,
                    newStatistics.getIncorrect_answers());

            detailValues.put(userRecord.accountDetails.COLUMN_ONE_NAME,
                    newUserDetails.getFirstName());
            detailValues.put(userRecord.accountDetails.COLUMN_TWO_NAME,
                    newUserDetails.getLastName());
            detailValues.put(userRecord.accountDetails.COLUMN_THREE_NAME,
                    newUserDetails.getAccount_id_fk());
            detailValues.put(userRecord.accountDetails.COLUMN_FOUR_NAME,
                    newUserDetails.getStat_id_fk());
            detailValues.put(userRecord.accountDetails.COLUMN_FIVE_TYPE,
                    newUserDetails.getAccount_details_id_pk());

            long newCredRow = writableDatabase.insertOrThrow(userRecord.accountCredentials.TABLE_NAME,
                    null, credentialValues);
            long newStatRow = writableDatabase.insertOrThrow(userRecord.accountStatistics.TABLE_NAME,
                    null, statisticsValues);
            long detailRow = writableDatabase.insertOrThrow(userRecord.accountDetails.TABLE_NAME,
                    null, detailValues);


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return thisUser;

    }

    public String createHashedPassword(String password, byte[] salt) {
        String hashedPassword = "";
        try {
            MessageDigest shaDigest = MessageDigest.getInstance("SHA256");
            //add a salt to the digest
            shaDigest.update(salt);
            byte[] passwordBytes = shaDigest.digest(password.getBytes());
            // create hex string to hashed password to database
            StringBuilder hexPassword = new StringBuilder();
            for (int i = 0; i < passwordBytes.length; i++) {
                //byte has to be ANDed and one must be added because java uses twos complement
                int currentHexadecimal = (passwordBytes[i] & 0xff) + 0x100;
                String twoComplementHex = Integer.toString(currentHexadecimal, 16);
                hexPassword.append(twoComplementHex);
            }
            hashedPassword = hexPassword.toString();
        } catch (NoSuchAlgorithmException nsa) {
            nsa.printStackTrace();
        }
        return hashedPassword;

    }

    public byte[] generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);
        return salt;
    }
}
