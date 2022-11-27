package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {
    Button loginbtn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this);
        //Log.d("helper_check", String.valueOf(DB.getData("sebby2").getCount()));
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView newAcc = (TextView) findViewById(R.id.createacc);
        loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //used to check login for now. correct login is admin admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin")
                && password.getText().toString().equals("admin")){
                    //correct
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    openHomeScreenActivity();
                } else{
                    //incorrect
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUpWindow();
            }
        });
    }

    private void openPopUpWindow() {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void openHomeScreenActivity(){
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }
}
