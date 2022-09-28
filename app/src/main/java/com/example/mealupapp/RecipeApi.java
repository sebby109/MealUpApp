package com.example.mealupapp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RecipeApi {
    private final OkHttpClient client = new OkHttpClient();

    RecipeApi(){

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
    public void getAPI(String srch){

        Request request = new Request.Builder()
                .url("https://edamam-recipe-search.p.rapidapi.com/search?q=" + srch)
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    Log.d("tester", "onResponse: " + responseBody.string());
                }
            }
        });

    }

}
