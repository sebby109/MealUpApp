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
        EditText et2 = (EditText) findViewById(R.id.height);
        EditText et3 = (EditText) findViewById(R.id.weight);
        EditText et4 = (EditText) findViewById(R.id.result);

        int num_age = Integer.parseInt(et1.getText().toString());
        int num_height = Integer.parseInt(et2.getText().toString());
        int num_weight = Integer.parseInt(et3.getText().toString());
        double result = 88.362 + (13.397 * num_weight) + (4.799 * num_height)-(5.677 * num_age);
        String stringdouble= Double.toString(result);
        et4.setText(stringdouble);

    }

    public void Female(View v) {
        EditText et1 = (EditText) findViewById(R.id.age);
        EditText et2 = (EditText) findViewById(R.id.height);
        EditText et3 = (EditText) findViewById(R.id.weight);
        EditText et4 = (EditText) findViewById(R.id.result);

        int num_age = Integer.parseInt(et1.getText().toString());
        int num_height = Integer.parseInt(et2.getText().toString());
        int num_weight = Integer.parseInt(et3.getText().toString());
        double result = 447.593 + (9.247 * num_weight) + (3.098 * num_height) - (4.330 * num_age);
        String stringdouble = Double.toString(result);
        et4.setText(stringdouble);
    }
}

//447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years)
//88.362 + (13.397 x weight in kg) + (4.799 x height in cm) – (5.677 x age in years) Women: BMR = 447.593 + (9.247 x weight in kg) + (3.098 x height in cm) – (4.330 x age in years



