package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This is a comment
        //There is a comment here too. hi
        //RecipeApi x = new RecipeApi();
        //SearchResults r = x.getFeed("tuna, lettuce");
        //String name = r.getNames().get(0);
        //String img = r.getImages().get(0);
        //String id = r.getIds().get(0);
        //x.findRecipe(id, img, name);

        //RecipeApi x = new RecipeApi();
        //SearchResults feed = x.getFeed("chicken soup", 1);

        button = (Button) findViewById(R.id.recipeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchBarActivity();
            }
        });
    }

    public void openSearchBarActivity(){
        Intent intent = new Intent(this, SearchBarActivity.class);
        startActivity(intent);
    }
}
