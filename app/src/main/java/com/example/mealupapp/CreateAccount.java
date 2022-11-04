package com.example.mealupapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.time.*;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private Button submit;
    private Button cancel;
    private TextView password;
    private TextView username;
    private TextView dob;
    private UserAccounts userAccounts;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        submit = (Button) findViewById(R.id.submitc);
        cancel = (Button) findViewById(R.id.cancelc);

        username = (TextView) findViewById(R.id.username2);
        password = (TextView) findViewById(R.id.password2);
        dob = (TextView) findViewById(R.id.bday);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePassword();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = username.getText().toString().trim();

        return true;
    }

    private void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void validateBDay(){
        //gets LocalDate instance year,month,date
        String str_dob = dob.getText().toString();
        String[] arr_dob = str_dob.split("-");
        LocalDate dob = LocalDate.of(Integer.valueOf(arr_dob[0]), Integer.valueOf(arr_dob[1]),
                Integer.valueOf(arr_dob[2]));
    }

    private void createUser(){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        // salts password. maybe will add a pepper.
        pass = pass + "P#!@t";
        int hash_salt_pass = pass.hashCode();

        User newUser = new User(user, hash_salt_pass, "20");
        userAccounts.addAccount(user, newUser);
    }
}