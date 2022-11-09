package com.example.mealupapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private SearchResults searches;
    private Context context;

    public recyclerAdapter(Context context, SearchResults searches,
                           RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.searches = searches;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gives look to rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        return new recyclerAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        //assigns values to views created on recycler_view_row file
        // based on position

        holder.name.setText(searches.getNames().get(position));
        Glide.with(context).load(searches.getImages().get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        // gives total items
        return searches.getNames().size();
    }

    public void clearData(){
        searches.clear();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabs views from recycler_view_row layout file and assigns
        // them to variables

        ImageView imageView;
        TextView name;
        //SearchView search_box;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView1);
            //search_box = search_box.findViewById(R.id.mySearchView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
