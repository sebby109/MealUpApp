package com.example.mealupapp;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String age;
    private ArrayList<String> recipes;
    private ArrayList<String> ingredients;
    private String security_q;

    User(String username, String password, String age){
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

    public Boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public void changePassword(String curr_pass, String new_pass){
        if(this.checkPassword(curr_pass))
            this.password = new_pass;
    }

    public String getAge(){
        return age;
    }

    public void updateAge(String age){
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

    public void changeSecurity(String new_sq, String password){
        if(this.checkPassword(password))
            this.security_q = new_sq;
    }
}
