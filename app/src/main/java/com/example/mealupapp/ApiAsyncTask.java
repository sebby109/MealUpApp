package com.example.mealupapp;

import android.os.AsyncTask;

public class ApiAsyncTask extends AsyncTask<String, Integer, SearchResults> {
    SearchResults searchResults;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected SearchResults doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(SearchResults searchResults) {
        super.onPostExecute(searchResults);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
