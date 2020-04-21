package com.example.basic_geo_quiz_app.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.basic_geo_quiz_app.R;
import com.example.basic_geo_quiz_app.data.model.userRecord;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        RegisterViewModel registerViewModel = ViewModelProviders.of(this, new RegisterViewModelFactory())
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

        TextWatcher afterTextChangeListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
