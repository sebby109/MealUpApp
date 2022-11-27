package com.example.mealupapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import java.time.Period;
import java.time.LocalDate;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    private Button submit;
    private Button cancel;
    private TextView password;
    private TextView username;
    private TextView dob;
    private DBHelper DB;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
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
        DB = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(validateBDay() && validatePassword() && validateUsername()){
                    createUser();
                    openLogin();
                }
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
        //need to create a patter for this.
        String usernameInput = username.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() < 4) {
            username.setError("Username must be at least 6 characters");
            return false;
        } else if (usernameInput.length() > 10) {
            username.setError("Username too long");
            return false;
        } else {
            username.setError(null);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean validateBDay(){
        String bdayInput = dob.getText().toString().trim();
        int n = bdayInput.length();

        if (bdayInput.isEmpty()) {
            dob.setError("Field cannot be empty");
            return false;
        } else if (n < 10 || n > 10) {
            dob.setError("Invalid input");
            return false;
        } else if (!bdayInput.matches("(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])")) {
            dob.setError("Invalid input");
            return false;
        }
        else {
            dob.setError(null);
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getAge(String bday){
        String[] bday_split = bday.split("-");
        int year = Integer.valueOf(bday_split[0]);
        int month =  Integer.valueOf(bday_split[1]);
        int day =  Integer.valueOf(bday_split[2]);

        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);

        return Period.between(birthDate, today).getYears();
    }

    private void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createUser(){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        int age = getAge(dob.getText().toString());
        // salts password
        pass = pass + "P#!@t";
        int hash_salt_pass = pass.hashCode();

        Boolean check = DB.insertUserData(user, hash_salt_pass, age);

        if(check){
            Log.d("created", "working");
            Toast.makeText(CreateAccount.this, "Account created!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(CreateAccount.this, "failed", Toast.LENGTH_SHORT).show();
        }

    }
}