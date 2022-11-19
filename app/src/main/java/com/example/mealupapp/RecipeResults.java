package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_results);

        SingleRecipe recipe = getIntent().getParcelableExtra("recipe");
        int i = recipe.getNutrition().size();
        int k = recipe.getInstructions().size();

        TextView title = findViewById(R.id.rTitle);
        ImageView imageView = findViewById(R.id.rPicture);
        TextView descriptions = findViewById(R.id.rDescriptions);

        title.setText(recipe.getRecipe_name());
        Glide.with(this).load(recipe.getImg_url()).into(imageView);

        descriptions.append(recipe.getDescription() + "\n\n");
        descriptions.append("Servings:\n" + recipe.getServings() + "\n\n");

        String[] labels = {"Sugar: ", "Carbs: ", "Fiber: ", "Protein: ", "Fat: ",
        "Calories: "};
        ArrayList<String> nutrient = recipe.getNutrition();
        for(int s=0; s < i; s++){
            if(nutrient.isEmpty())
                break;
            if(s == 0)
                descriptions.append("Per serving:");
            descriptions.append("\n" + labels[s] + nutrient.get(s));
        }

        String prep = recipe.getPrep_time();
        String cook = recipe.getCook_time();

        if(!prep.equals("null"))
            descriptions.append("\n\n" + prep);
        if(!cook.equals("null"))
            descriptions.append("\n\n" + cook);

        ArrayList<String> instructions = recipe.getInstructions();
        for(int z=0; z < k; z++){
            if(instructions.isEmpty())
                break;
            if(z == 0)
                descriptions.append("Instructions:");
            descriptions.append("\n" + String.valueOf(z + 1)+ ".) " + instructions.get(z));
        }

        descriptions.append("\n\nCredit: " + recipe.getCredit_name() + "\n@Tasty (tasty.co)");
    }
}