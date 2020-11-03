package com.example.ss4pk;

import android.content.Context;
import android.os.Vibrator;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transform {
    public static Boolean StringNoNull(String string){
        if(string == null)
            return false;
        else return string.length() !=0;
    }
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
    public static void Vibrate(Context context){
        //1 secunda = 1000 milli
        long mills = 1000L;
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator.hasVibrator()){
            vibrator.vibrate(mills);
        }
    }
    public static String md5Custom(String st){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try{
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1,digest);
        String md5Hex = bigInt.toString(16);
        while(md5Hex.length()<32){
            md5Hex = "0"+md5Hex;
        }
        return md5Hex;
    }
}
