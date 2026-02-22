// Fault 5 Missed by Code Coverage, but caught by Branch Coverage

package com.fitzgerald;

public class Fault5 {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
    
        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        if (kwh > 500 && kwh <= 1500) {
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.15;
            } else if (hasSmartDevice || peakOptOut) {
                rebatePercent = 0.10;
            }
        } else if (kwh > 1500) {
            if (hasSmartDevice || peakOptOut) {     // FAULT: changed the && condtion to an ||
                                                    // my test cases are current (true, false) & (false, false)
                                                    // so, false true will never actually be tested here 
                rebatePercent = 0.20;   
            } else {
                rebatePercent = 0.05;   
            }
        }

        return rebatePercent;
    }
}



