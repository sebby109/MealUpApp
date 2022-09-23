package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is a comment
        RecipeApi x = new RecipeApi();
        Log.i("test", String.valueOf(x.getI()));
    }
}