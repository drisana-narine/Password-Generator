package com.PersonalProjects;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class PasswordMaker {
    private static Hashtable<Integer, String> info;
    private static String numbers;
    private static boolean needsCapitals;

    public static void main(String[] args) {
        //collect information from user
        Scanner keyboard  = new Scanner(System.in);
        System.out.println("Initials: ");
        String initials = keyboard.nextLine();
        System.out.println("Favorite Color: ");
        String color = keyboard.nextLine();
        System.out.println("Favorite Movie: ");
        String movie = keyboard.nextLine();
        System.out.println("Favorite Animal: ");
        String animal = keyboard.nextLine();
        System.out.println("Name of High School Attended: ");
        String hs = keyboard.nextLine();
        System.out.println("City Born in: ");
        String city = keyboard.nextLine();
        //put all numerical answers in 1 String to pick a random digit later
        System.out.println("Favorite Number: ");
        numbers = keyboard.nextLine();
        System.out.println("Number of siblings: ");
        numbers+= keyboard.nextLine();
        System.out.println("Favorite birthday: ");
        numbers+= keyboard.nextLine();

        //put text answers in hash table with numbers as keys
        info = new Hashtable<>();
        info.put(0,initials);
        info.put(1, color);
        info.put(2, movie);
        info.put(3,animal);
        info.put(4, hs);
        info.put(5,city);

        //ask about password requirements
        System.out.println("How many characters should the password be? ");
        int length = keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Does your password need capital letters?");
        String response= keyboard.nextLine();
        if(response.equalsIgnoreCase("yes")){
            needsCapitals = true;
        }
        else {
            needsCapitals = false;
        }
        System.out.println("***************************************************************************");
        System.out.println("Enter 'L' for a password with just letters.");
        System.out.println("Enter 'N' for a password with letters and numbers.");
        System.out.println("Enter 'S' for a password with letters, numbers, and special characters");
        System.out.println("***************************************************************************");
        String type= keyboard.nextLine();
        switch(type) {
            case "L":
                System.out.println("Your password is: "+justLettersPassword(length));
                break;
            case "N":
                System.out.println("Your password is: "+lettersAndNumberPassword(length));
                break;
            case "S":
                System.out.println("Your password is: "+lettersNumbersSpecialCharactersPassword(length));
                break;
        }

    }

    //method to pick two random letters from words in hashtable
    private static String randomLetters(String s){
        if(s.length()==2){
            return s;
        }
        else{
            //get rid of spaces in answer
            String s2="";
            for(int i=0;i<s.length();i++){
                if(!Character.isWhitespace(s.charAt(i))){
                    s2+= s.charAt(i);
                }
            }
            //pick two random letters from random word from hashtable
            char[] letters = s2.toCharArray();
            int choice;
            String twoLetters = "";
            for(int i =0; i<2;i++){
                choice = (int) (Math.random() * (s2.length()));
                twoLetters+=letters[choice];
            }
            return twoLetters;
        }
    }

    //make password with just letters
    private static String justLettersPassword(int length){
        int choice;
        String password="";
        //get two random letters from different words until correct length
        for(int i=0;i<length/2;i++){
            choice=(int) (Math.random() * (info.size()));
            password+=randomLetters(info.get(choice));
        }
        //capitalize if necessary
        if(needsCapitals)
            return randomCapitalization(password);
        else
            return password;
    }

    //make password with letters and a number
    private static String lettersAndNumberPassword(int length){
        int choice;
        String password="";
        //get two random letters from different words
        for(int i=0;i<length/2;i++){
            choice=(int) (Math.random() * (info.size()));
            password+=randomLetters(info.get(choice));
        }
        //cut one letter and capitalize if necessary
        password=password.substring(0,length-1);
        if(needsCapitals)
            password=randomCapitalization(password);
        //add number so password is correct length
        password+=pickANumber(numbers);
        return password;

    }

    //make password with letters, a number, and a special character
    private static String lettersNumbersSpecialCharactersPassword(int length){
        //subtract 2 to make room for number and special character
        length=length-2;
        int choice;
        String password="";
        //get two random letters from different words
        for(int i=0;i<length/2;i++){
            choice=(int) (Math.random() * (info.size()));
            password+=randomLetters(info.get(choice));
        }
        //capitalize if necessary
        if(needsCapitals)
            password=randomCapitalization(password);
        //add number and special character
        password+=pickANumber(numbers)+pickASpecialCharacter();
        return password;
    }

    //pick random number from the String made of numbers user entered
    private static char pickANumber(String s){
        char[] numbers = s.toCharArray();
        int choice = (int) (Math.random() * (s.length()));
        return numbers[choice];
    }

    //pick random special character
    private static String pickASpecialCharacter(){
        String[] specialCharacters = {"~","`","!","@","#","$","%","^","&","*","(",")", "_",
                "-","+","=","{","[","}","]","|","\\",":",";","\"","'","<",",",">",".", "?","/"};
        int choice = (int) (Math.random() * specialCharacters.length);
        return specialCharacters[choice];
    }

    //capitalize random letters
    private static String randomCapitalization(String s){
        String capitalizedPassword="";
        //minimum 1 capital letter, maximum all but one
        int max = s.length();
        int min =1;
        int times = (int) ((Math.random() * (max - min)) + min);
        ArrayList<Integer> makeCapital = new ArrayList<>();
        for(int i=0;i<times;i++){
            makeCapital.add((int) ((Math.random() * (max - min)) + min));
        }
        //capitalize letters
        for(int i=0;i<s.length();i++){
            if(makeCapital.contains(i)){
                char d= s.charAt(i);
                d=Character.toUpperCase(d);
                capitalizedPassword+=d;
            }
            else
                capitalizedPassword+=s.charAt(i);
        }
        return capitalizedPassword;
    }
}

