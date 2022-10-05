package com.example.mealupapp;

import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class IngredientsAPI {
    private final OkHttpClient client;
    private ArrayList<String> names;
    private ArrayList<String> ids;
    private ArrayList<String> images;
    private SearchResults myResults;
    private singleRecipe myRecipe;

    IngredientsAPI(){
        client = new OkHttpClient();
        names = new ArrayList<String>();
        ids = new ArrayList<String>();
        images = new ArrayList<String>();
        myResults = new SearchResults();
        myRecipe = new singleRecipe();
    }

    private void getSearchFeed(String qry){
        // Obtains JSON data from our API
        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?ingredients=" +  qry
                        + "&ignorePantry=true&ranking=1")
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        //Asych call to check if request successful
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // here will change to also take care if API is down
                // it will also change app screen to let user
                // know that App is down.
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    String strResponse = responseBody.string();
                    //Log.d("search1", strResponse);
                    parseFeedData(strResponse);

                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseFeedData(String data){

        JSONObject jsonObject = null;
        try {
            JSONArray jsonArray = new JSONArray(data);
            int n = jsonArray.length();

            for(int i=0; i<n; i++) {
                JSONObject tmp = (JSONObject) jsonArray.get(i);
                myResults.addName(tmp.getString("title"));
                myResults.addImage(tmp.getString("image"));
                myResults.addId(tmp.getString("id"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRecipeData(String id){
        // Obtains JSON data from our API

        Request request = new Request.Builder()
                .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + id + "/information")
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        //Asych call to check if request successful
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // here will change to also take care if API is down
                // it will also change app screen to let user
                // know that App is down.
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    String strResponse = responseBody.string();
                    Log.d("search10", strResponse);
                    //findRecipeData(strResponse);

                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void parseRecipeData(String data){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            myRecipe.setServings(jsonObject.getString("servings"));
            myRecipe.setDescription(jsonObject.getString("summary"));
            myRecipe.setCook_time(jsonObject.getString("cookingMinutes"));
            myRecipe.setPrep_time(jsonObject.getString("preparationMinutes"));
            myRecipe.setCredit_name(jsonObject.getString("sourceUrl"));
            myRecipe.addToInstructions(jsonObject.getString("instructions"));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getRecipeArray(String data) {
            JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                myRecipe.addToIngredients(object.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String cleanString(String qry){
        String cleaned = qry.replaceAll("[^A-zA-Z0-9\\p{javaWhitespace}]", " ");
        cleaned = cleaned.trim().replaceAll("\\p{javaWhitespace}{2,}", " ");
        cleaned = cleaned.replace(" ", "%2C%20");

        return cleaned;
    }

    public SearchResults getFeed(String qry){

        String cleaned_str = cleanString(qry);
        getSearchFeed(cleaned_str);
        return myResults;
    }

    public void findRecipe(String id, String img, String name){
        myRecipe.setRecipe_name(name);
        myRecipe.setImg_url(img);
        myRecipe.setId(id);
        getRecipeData(id);
    }
}
