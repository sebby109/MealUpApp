package com.example.mealupapp;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RecipeApi {
    private final OkHttpClient client;

    RecipeApi(){
    client = new OkHttpClient();
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

        // Obtains JSON data from our API
        Request request = new Request.Builder()
                .url("https://edamam-recipe-search.p.rapidapi.com/search?q=" + srch)
                .get()
                .addHeader("X-RapidAPI-Key", "a122f83c62msh9cc7775d532a038p19b977jsn86ba7194c4da")
                .addHeader("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .build();

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
                    String strResponse = responseBody.string().replace("{", "").replace("}", "");
                    //makeHashMap(strResponse);
                    Log.d("tester", "onResponse: " + strResponse);
                }
            }
        });

    }

    /*
    * This private method will extract the needed
    * key and value pairs from the string response from
    * the API. And return it as a HashMap.
    *
    * params
    * resStr, String
    *
    * returns
    * None
    * */
    private void makeHashMap(String resStr){
        Map<String, String> retval = new HashMap<String, String>();
        String pieces[] = resStr.split(",");

        for (String piece : pieces) {

            //Splits data by colon to separate the key
            // and value
            String resData[] = piece.split(":");

            String keyVal = resData[0].trim();
            String val = resData[1].trim();

            // Add to map
            retval.put(keyVal, val);
        }

        Log.d("hash test", retval.toString());
    }

}
