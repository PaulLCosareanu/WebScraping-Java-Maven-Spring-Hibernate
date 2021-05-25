/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.paulcosareanu.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author cosar
 */
public class StringSplitter {
/**
 * 
 * @param str text string  parsed, a pattern match algorithm is then used to find patterns within the text
 * @param br text string  parsed, a pattern match algorithm is then used to find patterns within the text, patterns like numbers and paranthesis will be ignored, the result will be used to find patterns on variable str
 * @return returns the brand of the product
 */
    public static boolean splitBrand(String str, String br) {
//          String quantity="";
        String brand = "";
        boolean found = false;

        String pattern = "(\\w+)";
//                          int price=0;

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(br);
        if (m.find()) {
            brand = m.group();
        } else {
            System.out.println("NO MATCH");
        }

        pattern = brand;
        // Create a Pattern object
        r = Pattern.compile(pattern);

        // Now create matcher object.
        m = r.matcher(str);
        if (m.find()) {
            found = true;
        } else {
            found = false;
        }

        return found;
    }
/**
 * 
 * @param str is the text which is compared with certain words e.g milk, skimmed, cheese, and if the words match any substring from the text, it is copied in a separate string
 * @return returns the processed string
 */
    public static String splitType(String str) {
        String[] tags = {"Semi", "semi", "Skimmed", "skimmed", "Organic", "organic", "Milk", "milk", "Whole", "whole", "Cheese", "cheese", "Cheddar", "cheddar", "Mature", "mature", "extra", "Extra", "mozzarella", "Mozzarella", "grated", "Grated", "Ball", "ball", "pearls", "Pearls","stilton","Stilton","blue","Blue","Cottage","cottage"};
        String quantity = "";
        String[] splitStr = str.split("[- ,\\/]");
        for (int i = 0; i < splitStr.length; i++) {
//            if(splitStr[i].matches("\\d+$")){
            for (int j = 0; j < tags.length; j++) {
                if (splitStr[i].contains(tags[j])) {
                    quantity = quantity + splitStr[i] + " ";
                }
            }
//            }

        }
        return quantity;
    }
/**
 * 
 * @param str text string  parsed, a pattern match algorithm is then used to find patterns within the text
 * @return the quantity or volume of a product
 */
    public static String splitQuantity(String str) {
        String quantity = "";
        String line = "Tesco whole milk semi skimmed milk 3 litres 4 pints 234ml 234 ml 23 l 2l 3 pts 3pt";
        String pattern = "(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(str);
        if (m.find()) {
            quantity = m.group();
        } else {
            System.out.println("NO MATCH");
        }

        return quantity;
    }
}
