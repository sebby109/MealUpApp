package com.example.mealupapp;

//import android.content.Intent;
import android.os.Bundle;
//import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class macro_calcul extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.macro_calculator);
    }

    public void Male(View v){
        EditText et1 = (EditText) findViewById(R.id.age);
        EditText et2 = (EditText) findViewById(R.id.height1);
        EditText et3 = (EditText) findViewById(R.id.height2);
        EditText et4 = (EditText) findViewById(R.id.weight);
        EditText et5 = (EditText) findViewById(R.id.result);
        EditText et6 = (EditText) findViewById(R.id.protein_result);
        EditText et7 = (EditText) findViewById(R.id.carbs_result);
        EditText et8 = (EditText) findViewById(R.id.fat_result);

        int num_age = Integer.parseInt(et1.getText().toString());
        int num_height1 = Integer.parseInt(et2.getText().toString());
        int num_height2 = Integer.parseInt(et3.getText().toString());
        int num_weight = Integer.parseInt(et4.getText().toString());
        double result = 66.5 + (13.75 * (num_weight * 0.45359237)) + (5.003 * ((num_height1 * 30.48) + (num_height2 * 2.54)))-(6.75 * num_age);
        double result_protein = (result * 0.2) / 4;
        double result2_carbs = (result * 0.5) / 4;
        double result3_fat = (result * 0.3) / 9;
        String string_result= Double.toString(result);
        String string_protein= Double.toString(result_protein);
        String string_carbs= Double.toString(result2_carbs);
        String string_fat= Double.toString(result3_fat);

        et5.setText(string_result);
        et6.setText(string_protein);
        et7.setText(string_carbs);
        et8.setText(string_fat);


    }
    public void Female(View v) {
        EditText et1 = (EditText) findViewById(R.id.age);
        EditText et2 = (EditText) findViewById(R.id.height1);
        EditText et3 = (EditText) findViewById(R.id.height2);
        EditText et4 = (EditText) findViewById(R.id.weight);
        EditText et5 = (EditText) findViewById(R.id.result);
        EditText et6 = (EditText) findViewById(R.id.protein_result);
        EditText et7 = (EditText) findViewById(R.id.carbs_result);
        EditText et8 = (EditText) findViewById(R.id.fat_result);

        int num_age = Integer.parseInt(et1.getText().toString());
        int num_height1 = Integer.parseInt(et2.getText().toString());
        int num_height2 = Integer.parseInt(et3.getText().toString());
        int num_weight = Integer.parseInt(et4.getText().toString());
        double result = 655.1 + (9.563 * (num_weight * 0.45359237)) + (1.850 * ((num_height1 * 30.48) + (num_height2 * 2.54)))-(4.676 * num_age);
        double result_protein = (result * 0.2) / 4;
        double result2_carbs = (result * 0.5) / 4;
        double result3_fat = (result * 0.3) / 9;
        String string_result= Double.toString(result);
        String string_protein= Double.toString(result_protein);
        String string_carbs= Double.toString(result2_carbs);
        String string_fat= Double.toString(result3_fat);

        et5.setText(string_result);
        et6.setText(string_protein);
        et7.setText(string_carbs);
        et8.setText(string_fat);
    }
}

//For men: BMR = 66.5 + (13.75 * weight in kg) + (5.003 * height in cm) - (6.75 * age)
//For women: BMR = 655.1 + (9.563 * weight in kg) + (1.850 * height in cm) - (4.676 * age)
//45–65% of your daily calories from carbs, 20–35% from fats and 10–35% from protein.



