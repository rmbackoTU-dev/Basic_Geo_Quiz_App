package com.example.basic_geo_quiz_app.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.basic_geo_quiz_app.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

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

        
    }
}
