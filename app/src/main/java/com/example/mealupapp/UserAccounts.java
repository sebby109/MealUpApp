package com.example.mealupapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public final class UserAccounts {
    private static HashMap<String, User> accounts = new HashMap<String, User>();

    private UserAccounts(){
    }

    public static void addAccount(String username, User user){
        accounts.put(username, user);
    }

    public static Boolean verifyAccount(String username, String password){
        // comment for commit
        if(!accounts.containsKey(username))
            return false;
        return true;
    }

    public static int number_of_accounts(){
        return accounts.size();
    }
}
