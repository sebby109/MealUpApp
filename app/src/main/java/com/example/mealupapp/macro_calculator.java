package com.example.mealupapp;

import java.util.Scanner;

public class macro_calculator {
    public static void macro_cal(String[] args){
        //Variables
        int age;
        int height;
        int weight;
        String sex = null;
        int BMR; // BMR = Basal Metabolic Rate
        boolean male;
        String exercise;
        boolean none = false;
        boolean light = false;
        boolean moderate = false;
        boolean intense = false;
        boolean five;
        double cal;

        //Asking the user for their gender
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is your sex? M or F (Please select one): ");
        sex = keyboard.nextLine();

        //Determining BMR
        System.out.println("Age: ");
        age = keyboard.nextInt();
        System.out.println("Weight: ");
        weight = keyboard.nextInt();
        System.out.println("Height: ");
        height = keyboard.nextInt();

        //Setting the character for gender
        char sexChar = sex.charAt(0);

        // Calculating BMR
        if (sexChar == 'M'){
            BMR = (int) (66 + (6.23 * weight) + (12.7 * height) - (6.8 * age));
        }else{
            BMR = (int) (665 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }
        //Show BMR
        System.out.println("Your current BMR is: " + BMR);

        //calculating daily caloric intake
        System.out.println("What is your level of exercise? (none, light, moderate, intense): ");
        System.out.println("Type none if you do not exercise");

    }
}