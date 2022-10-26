package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;

public class SearchBarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchResults searchResults;
    private SearchView searchView;
    private String qry;
    private RecipeApi recipeApi;
    private IngredientsAPI ingredientsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        String searchType = getIntent().getStringExtra("btn");
        Boolean isRecipe = false;

        if(searchType.equals("recipe"))
            isRecipe = true;

        if(isRecipe){
            recipeApi = new RecipeApi();
            searchResults = recipeApi.getFeed(qry);
        } else{
            ingredientsAPI = new IngredientsAPI();
            searchResults = ingredientsAPI.getFeed(" ");
        }

        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerAdapter adapter = new recyclerAdapter(this, searchResults);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.mySearchView);
        searchView.clearFocus();

        Boolean finalIsRecipe = isRecipe;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(adapter.getItemCount() == 0) {
                    if(finalIsRecipe)
                        searchResults = recipeApi.getFeed(query);
                    else
                        searchResults = ingredientsAPI.getFeed(query);
                }
                else{
                    adapter.clearData();
                    if(finalIsRecipe)
                        searchResults = recipeApi.getFeed(query);
                    else
                        searchResults = ingredientsAPI.getFeed(query);;
                }
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}