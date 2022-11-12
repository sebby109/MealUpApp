package com.example.mealupapp;

import android.util.Log;
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

public class RecipeApi {
    private final OkHttpClient client;
    private ArrayList<String> names;
    private ArrayList<String> ids;
    private ArrayList<String> images;
    private SearchResults myResults;
    private SingleRecipe myRecipe;

    RecipeApi(){
        client = new OkHttpClient();
        names = new ArrayList<String>();
        ids = new ArrayList<String>();
        images = new ArrayList<String>();
        myResults = new SearchResults();
        myRecipe = new SingleRecipe();
    }

    /*
    * This method uses an Asynchronous get
    * to retrieve a JSON string from
    * the api query. It then converts that to a string.
    * The caller sends in what they want searched (srch)
    * and method will return results.
    *
    * params
    * srch, String
    *
    * return
    * None
    * */
    private void getSearchFeed(String qry){
        String start = "1";

        // Obtains JSON data from our API
        Request request = new Request.Builder()
                .url("https://tasty.p.rapidapi.com/recipes/list?from=0&size=100&q=" + qry)
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "tasty.p.rapidapi.com")
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
                    getFeedData(strResponse);
                    //recipeCallback.displayFeed(myResults);
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

    private void getRecipeData(String id){
        // Obtains JSON data from our API

        Request request = new Request.Builder()
                .url("https://tasty.p.rapidapi.com/recipes/get-more-info?id=" + id)
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "tasty.p.rapidapi.com")
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

                    findRecipeData(strResponse);

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

    private void findRecipeData(String data){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            myRecipe.setServings(jsonObject.getString("num_servings"));
            myRecipe.setImg_url(jsonObject.getString("thumbnail_url"));
            myRecipe.setRecipe_name(jsonObject.getString("name"));
            myRecipe.setDescription(jsonObject.getString("description"));
            myRecipe.setCook_time(jsonObject.getString("cook_time_minutes"));
            myRecipe.setPrep_time(jsonObject.getString("prep_time_minutes"));

            JSONArray instruArray = jsonObject.getJSONArray("instructions");

            if(instruArray.length() != 0)
                getRecipeArrays(instruArray, "instructions");
            JSONArray creditsArray = jsonObject.getJSONArray("credits");

            if(creditsArray.length() != 0)
                getRecipeArrays(creditsArray, "credits");

            // had to do nutrition values this way because it is not an array.s
            JSONObject nutritions = jsonObject.getJSONObject("nutrition");

            if(nutritions.length() != 0)
                addNutritions(nutritions);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getRecipeArrays(JSONArray arr, String name) {

        if (name.equals("instructions")) {
            try {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject object = arr.getJSONObject(i);
                    myRecipe.addToInstructions(object.getString("display_text"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        else if (name.equals("credits")) {
            try {
                JSONObject object = arr.getJSONObject(0);
                myRecipe.setCredit_name(object.getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void addNutritions(JSONObject nutriObj){
        try {
            myRecipe.addToNutrition(nutriObj.getString("sugar"));
            myRecipe.addToNutrition(nutriObj.getString("carbohydrates"));
            myRecipe.addToNutrition(nutriObj.getString("fiber"));
            myRecipe.addToNutrition(nutriObj.getString("protein"));
            myRecipe.addToNutrition(nutriObj.getString("fat"));
            myRecipe.addToNutrition(nutriObj.getString("calories"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getFeedData(String data){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);

                try{
                    // passes any recipe that is not actually
                    // a recipe but a list of recipes.
                    String test = object.getString("recipes");
                    continue;
                }
                catch (JSONException e){
                    // does not need to do anything if the recipe
                    // in the search is a recipe and not
                    // a list of recipes. The error occurs
                    // when the search is a regular recipe
                    // because it will not have "recipes"
                    // in its array.
                }

                String recipe_name = object.getString("name");
                String recipe_id = object.getString("id");
                String img_url = object.getString("thumbnail_url");
                myResults.addId(recipe_id);
                myResults.addImage(img_url);
                myResults.addName(recipe_name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public SearchResults getFeed(String qry){
        getSearchFeed(qry);
        return myResults;
    }

    public SingleRecipe recipeInfo(String id){
        getRecipeData(id);
        myRecipe.setId(id);
        return myRecipe;
    }

}
