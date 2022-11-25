package com.example.mealupapp;

import java.util.ArrayList;

public class User {
    private String username;
    private int password;
    private int age;
    private ArrayList<String> recipes;
    private ArrayList<String> ingredients;
    private String security_q;

    User(String username, int password, int age){
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean checkPassword(int password){
        return this.password == password;
    }

    public void changePassword(int curr_pass, int new_pass){
        if(this.checkPassword(curr_pass))
            this.password = new_pass;
    }

    public String getAge(){
        return String.valueOf(age);
    }

    public void updateAge(int age){
        this.age = age;
    }

    public ArrayList<String> getRecipes(){
        return recipes;
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public boolean checkSecurity(String security_q){
        return this.security_q.equals(security_q);
    }

    public void changeSecurity(String new_sq, int password){
        if(this.checkPassword(password))
            this.security_q = new_sq;
    }
}
