package com.example.cricketscore.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calculations {

    public static String StrikeRateCalculation(int run, int ball){
        float num=(float)run/ball;
        double strikeRate= num*100;
        return roundOff(strikeRate);
    }

    public static String EconomyCalculation(int run, int over){
        float num=(float)run/over;
        double economyRate= num;
        return roundOff(economyRate);
    }

    public static String roundOff(double number){

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        String roundedNumber = decimalFormat.format(number);

        // Displaying the rounded number
        Log.d("RoundedNumber", roundedNumber);
        return roundedNumber;
    }
}
