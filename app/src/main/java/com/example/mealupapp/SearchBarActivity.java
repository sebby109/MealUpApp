package com.example.mealupapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.SearchView;

public class SearchBarActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private SearchResults searchResults;
    private SearchView searchView;
    private String qry;
    private RecipeApi recipeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        //String searchType = getIntent().getStringExtra("btn");

        recipeApi = new RecipeApi();
        searchResults = recipeApi.getFeed(qry);

        recyclerView = findViewById(R.id.myRecyclerView);
        recyclerAdapter adapter = new recyclerAdapter(this, searchResults, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.mySearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (adapter.getItemCount() != 0) {
                    adapter.clearData();
                }
                searchResults = recipeApi.getFeed(query);

                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SearchBarActivity.this, RecipeResults.class);
        SingleRecipe singleRecipe = recipeApi.recipeInfo(searchResults.getIds().get(position));
        Log.d("recipe_pos", String.valueOf(searchResults.getIds().get(position)));
        intent.putExtra("recipe", singleRecipe);
        startActivity(intent);
    }
}