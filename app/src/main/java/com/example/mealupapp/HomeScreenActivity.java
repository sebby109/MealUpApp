package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeScreenActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        UserAccounts userAccounts = new UserAccounts();
        Log.d("wrong", userAccounts.verifyAccount("bob", "bob"));
        button = findViewById(R.id.recipeButton);
        button.setOnClickListener(v -> openSearchActivity("recipe"));
    }

    public void openSearchActivity(String type){
        Intent intent = new Intent(this, SearchBarActivity.class);
        intent.putExtra("btn", type);
        startActivity(intent);
    }
}