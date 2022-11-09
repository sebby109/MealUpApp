package com.example.mealupapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SingleRecipe implements Parcelable {
    private String id;
    private String servings;
    private String img_url;
    private ArrayList<String> nutrition;
    private String recipe_name;
    private String description;
    private String cook_time;
    private String prep_time;
    private ArrayList<String> instructions;
    private String credit_name;

    private ArrayList<String> ingredients;

    public SingleRecipe() {

        this.nutrition = new ArrayList<String>();
        this.instructions = new ArrayList<String>();
        this.ingredients = new ArrayList<String>();

    }

    protected SingleRecipe(Parcel in) {
        id = in.readString();
        servings = in.readString();
        img_url = in.readString();
        nutrition = in.createStringArrayList();
        recipe_name = in.readString();
        description = in.readString();
        cook_time = in.readString();
        prep_time = in.readString();
        instructions = in.createStringArrayList();
        credit_name = in.readString();
        ingredients = in.createStringArrayList();
    }

    public static final Creator<SingleRecipe> CREATOR = new Creator<SingleRecipe>() {
        @Override
        public SingleRecipe createFromParcel(Parcel in) {
            return new SingleRecipe(in);
        }

        @Override
        public SingleRecipe[] newArray(int size) {
            return new SingleRecipe[size];
        }
    };

    public void addToNutrition(String name){
        nutrition.add(name);
    }

    public void addToInstructions(String name){
        instructions.add(name);
    }

    public void addToIngredients(String name){ ingredients.add(name); }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    /*
    * Order of nutrition is sugar, carbs, fiber,
    * protein, fat, and calories.
    * */
    public ArrayList<String> getNutrition() {
        return nutrition;
    }

    public void setNutrition(ArrayList<String> nutrition) {
        this.nutrition = nutrition;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCook_time() {
        return cook_time;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public String getCredit_name() {
        return credit_name;
    }

    public void setCredit_name(String credit_name) {
        this.credit_name = credit_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(servings);
        dest.writeString(img_url);
        dest.writeStringList(nutrition);
        dest.writeString(recipe_name);
        dest.writeString(description);
        dest.writeString(cook_time);
        dest.writeString(prep_time);
        dest.writeStringList(instructions);
        dest.writeString(credit_name);
        dest.writeStringList(ingredients);
    }
}
