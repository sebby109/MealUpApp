package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_results);

        SingleRecipe recipe = getIntent().getParcelableExtra("recipe");

        TextView title = findViewById(R.id.rTitle);
        ImageView imageView = findViewById(R.id.rPicture);
        title.setText(recipe.getRecipe_name());
        Glide.with(this).load(recipe.getImg_url()).into(imageView);
    }
}