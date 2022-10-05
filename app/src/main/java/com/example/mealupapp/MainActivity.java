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
        //There is a comment here too. hi
        RecipeApi x = new RecipeApi();
        //SearchResults r = x.getFeed("tuna, lettuce");
        //String name = r.getNames().get(0);
        //String img = r.getImages().get(0);
        //String id = r.getIds().get(0);
        //x.findRecipe(id, img, name);
        SearchResults feed = x.getFeed("chicken soup", 1);


    }
}
