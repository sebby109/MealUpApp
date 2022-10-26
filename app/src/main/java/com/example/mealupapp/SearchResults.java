package com.example.mealupapp;

import java.io.IOException;
import java.util.ArrayList;

public class SearchResults {
    private ArrayList<String> ids;
    private ArrayList<String> images;
    private ArrayList<String> names;

    SearchResults(){
        ids = new ArrayList<String>();
        images = new ArrayList<String>();
        names = new ArrayList<String>();
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public void addId(String id){
        ids.add(id);
    }

    public void addImage(String image){
        images.add(image);
    }

    public void addName(String name){
        names.add(name);
    }

    public void clear(){
        ids.clear();
        names.clear();
        images.clear();
    }
}
