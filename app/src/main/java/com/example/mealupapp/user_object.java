package com.example.mealupapp;
import java.util.Scanner;

public class user_object {
    public void username(){
        Scanner userobj = new Scanner(System.in);
        System.out.println("Enter username: ");

        String userName = userobj.nextLine();
        System.out.println(userName);
    };

    public void password(){
        Scanner userobj = new Scanner(System.in);
        System.out.println("Enter password: ");

        String pword = userobj.nextLine();
        System.out.println(pword);
    };
    
}
