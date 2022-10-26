package com.example.mealupapp;

import java.util.HashMap;

public class UserAccounts {
    private HashMap<String, User> accounts;

    UserAccounts(){
        accounts = new HashMap<String, User>();
    }

    public void addAccount(String username, User user){
        accounts.put(username, user);
    }

    public String verifyAccount(String username, String password){
        if(!accounts.containsKey(username))
            return "nope";
        return "yes";
    }
}
