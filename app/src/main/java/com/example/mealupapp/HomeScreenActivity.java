package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {
    private Button button;
    private TextView displayUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");

        displayUser = findViewById(R.id.hUsername);
        displayUser.append(user.getUsername());
        button = findViewById(R.id.recipeButton);
        button.setOnClickListener(v -> openSearchActivity());
    }

    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }
}