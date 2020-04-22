package com.example.basic_geo_quiz_app.ui.register;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.basic_geo_quiz_app.R;
import com.example.basic_geo_quiz_app.data.model.dbHelper;
import com.example.basic_geo_quiz_app.data.model.userRecord;
import com.example.basic_geo_quiz_app.ui.login.QuizLoginActivity;

public class CreateAccountActivity extends AppCompatActivity {

    dbHelper registrationDatabaseHelper = new dbHelper(this);
    SQLiteDatabase readableDatabase = registrationDatabaseHelper.getReadableDatabase();
    SQLiteDatabase writableDatabase = registrationDatabaseHelper.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        final RegisterViewModel registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory())
                .get(RegisterViewModel.class);

        final EditText firstName = findViewById(R.id.firstNameEdit);
        final EditText lastName = findViewById(R.id.lastNameEdit);
        final EditText email = findViewById(R.id.emailEditText);
        final EditText password = findViewById(R.id.passwordEditText);
        final EditText repeatPassword = findViewById(R.id.passwordRepeatEditText);
        final TextView firstNameError = findViewById(R.id.registerError);
        final TextView secondNameError = findViewById(R.id.registerError2);
        final TextView emailError = findViewById(R.id.registerError3);
        final TextView passwordError = findViewById(R.id.registerError4);
        final TextView repeatPasswordError = findViewById(R.id.registerError5);
        final Button registerButton = findViewById(R.id.registerButton);

        registerViewModel.getRegistrationForm().observe(this, new Observer<RegistrationFormState>() {
            @Override
            public void onChanged(@Nullable RegistrationFormState registrationFormState) {
                if (registrationFormState == null) {
                    return;
                }
                registerButton.setEnabled(registrationFormState.isDataValid());
                if (registrationFormState.getFirstNameError() != null) {
                    firstNameError.setError(getString(registrationFormState.getFirstNameError()));
                }
                if (registrationFormState.getLastNameError() != null) {
                    firstNameError.setError(getString(registrationFormState.getLastNameError()));
                }
                if (registrationFormState.getEmailError() != null) {
                    firstNameError.setError(getString(registrationFormState.getEmailError()));
                }
                if (registrationFormState.getPasswordError() != null) {
                    firstNameError.setError(getString(registrationFormState.getPasswordError()));
                }
                if (registrationFormState.getRepeatPasswordError() != null) {
                    firstNameError.setError(getString(registrationFormState.getRepeatPasswordError()));
                }
            }
        });

        registerViewModel.getRegistrationResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(RegisterResult registerResult) {
                if (registerResult == null) {
                    return;
                }
                if (registerResult.getError() != null) {
                    registrationFailed(registerResult.getError());
                } else if (registerResult.getSuccess() != null) {
                    registrationSuccess(registerResult.getSuccess());
                }
            }
        });


        TextWatcher afterTextChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registrationDataChanged(firstName.getText().toString(),
                        lastName.getText().toString(), email.getText().toString(),
                        password.getText().toString(), repeatPassword.getText().toString());

            }
        };
        firstName.addTextChangedListener(afterTextChangeListener);
        lastName.addTextChangedListener(afterTextChangeListener);
        email.addTextChangedListener(afterTextChangeListener);
        password.addTextChangedListener(afterTextChangeListener);
        repeatPassword.addTextChangedListener(afterTextChangeListener);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int credid = getRowID(userRecord.accountCredentials.TABLE_NAME,
                        userRecord.accountCredentials.COLUMN_THREE_NAME);
                int detailid = getRowID(userRecord.accountDetails.TABLE_NAME,
                        userRecord.accountDetails.COLUMN_FIVE_NAME);
                int statid = getRowID(userRecord.accountStatistics.TABLE_NAME,
                        userRecord.accountStatistics.COLUMN_ONE_NAME);
                userRecord[] newUser = registerViewModel.registerUser(firstName.getText().toString(),
                        lastName.getText().toString(), email.getText().toString(),
                        password.getText().toString(), credid, statid, detailid);
                writeNewUserAccountToDatabase(newUser);
            }
        });
    }

    private void writeNewUserAccountToDatabase(userRecord[] newUser) {
        try {


            //write newUser[0] to credential database
            userRecord.accountCredentials newUserCreds = (userRecord.accountCredentials)
                    newUser[0];
            ContentValues credentialValues = new ContentValues();
            credentialValues.put(userRecord.accountCredentials.COLUMN_ONE_NAME,
                    newUserCreds.getEmail());
            credentialValues.put(userRecord.accountCredentials.COLUMN_TWO_NAME,
                    newUserCreds.getPasswordHash());
            credentialValues.put(userRecord.accountCredentials.COLUMN_THREE_NAME,
                    newUserCreds.getAccount_id_pk());
            credentialValues.put(userRecord.accountCredentials.COLUMN_FOUR_NAME,
                    newUserCreds.getPasswordSalt());
            //write newUser[1] to statistics database
            userRecord.accountStatistics newUserStats = newUserStats = (userRecord.accountStatistics)
                    newUser[1];
            ContentValues statisticsValues = new ContentValues();
            statisticsValues.put(userRecord.accountStatistics.COLUMN_ONE_NAME,
                    newUserStats.getStat_id());
            statisticsValues.put(userRecord.accountStatistics.COLUMN_TWO_NAME,
                    newUserStats.getCorrect_answers());
            statisticsValues.put(userRecord.accountStatistics.COLUMN_THREE_NAME,
                    newUserStats.getIncorrect_answers());
            //write newUser[2] to details database
            userRecord.accountDetails newUserDetails = (userRecord.accountDetails) newUser[2];
            ContentValues detailValues = new ContentValues();
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
            writableDatabase.insertOrThrow(userRecord.accountCredentials.TABLE_NAME,
                    null, credentialValues);
            writableDatabase.insertOrThrow(userRecord.accountStatistics.TABLE_NAME,
                    null, statisticsValues);
            writableDatabase.insertOrThrow(userRecord.accountDetails.TABLE_NAME,
                    null, detailValues);
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
    }


    public void registrationSuccess(RegisterUserView model) {
        String newAccount = model.getFirstName() + " your new account has been created!";
        Toast.makeText(getApplicationContext(), newAccount, Toast.LENGTH_LONG).show();
        Intent newIntent = new Intent(this, QuizLoginActivity.class);
        startActivity(newIntent);
    }

    public void registrationFailed(@StringRes Integer errString) {
        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_LONG);
    }

    public Integer getRowID(String tableName, String idRowName) {
        Integer id = null;
        try {
            String[] idProjection = {
                    idRowName
            };

            String selector = idRowName;

            Cursor cusor = readableDatabase.query(tableName, idProjection, selector, null,
                    null, null, "ORDER BY " +
                            selector + " DESC", "LIMIT 1");

            if (cusor.getCount() == 0) {
                id = 0;
            } else if (cusor.getCount() == 1) {
                id = cusor.getInt(0);
            }
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }

        return id;

    }
}
