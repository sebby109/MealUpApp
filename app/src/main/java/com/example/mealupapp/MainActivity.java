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
    Cursor cursor;
    User curr_user;
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
                String uName = username.getText().toString();
                String pass = password.getText().toString();

                cursor = DB.getData(uName, pass);
                int status = cursor.getCount();
                Log.d("checker2", String.valueOf(status));
                if(status > 0){
                    //correct
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    StringBuffer buffer = new StringBuffer();

                    while(cursor.moveToNext()){
                        String tmp_u = cursor.getString(0);
                        int tmp_p = cursor.getInt(1);
                        int tmp_a = cursor.getInt(2);
                        curr_user = new User(tmp_u, tmp_p, tmp_a);
                    }

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
        intent.putExtra("user", curr_user);
        startActivity(intent);
    }
}
