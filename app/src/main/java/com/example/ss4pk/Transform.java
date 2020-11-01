package com.example.ss4pk;

public class Transform {
    public static int parseIntOrDefault(String whatParse, int defaultValue) {
        int parse;
        try{
            parse = Integer.parseInt(whatParse);
        }
        catch (Exception NumberFormatException){
            parse = defaultValue;
        }
        return parse;
    }
}
