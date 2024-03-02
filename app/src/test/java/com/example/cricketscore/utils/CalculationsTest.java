package com.example.cricketscore.utils;

import junit.framework.TestCase;

public class CalculationsTest extends TestCase {

    public void testStrikeRateCalculation() {
        String res=Calculations.StrikeRateCalculation(100,100);
        assertEquals("100.00",res);
    }

    public void testEconomyCalculation() {
        String res=Calculations.EconomyCalculation(10,10);
        assertEquals("1.00",res);
    }

    public void testRoundOff() {
        String res=Calculations.roundOff(10.1123112);
        assertEquals("10.11",res);
    }
}