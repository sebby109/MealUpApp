package com.example.mealupapp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecipeApi {
    private static int i = 1;
    private int k = 0;

    RecipeApi(){

    }

    public int getI(){
        return i;
    }

    public int addI(){
        i += 1;
        return i;
    }
}
