// Fault 1 Caught by Equivalence Partitions 

package com.fitzgerald;

public class Fault1 {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
        // FAULT: changed kwh <= 0 to kwh < 0 
        // this will be caught by EP1 because 0 should produce INVALID as the expected results, but 
        // will now be 0.0 actual
        if (kwh < 0) {
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
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.20;
            } else {
                rebatePercent = 0.05;
            }
        }

        return rebatePercent;
    }
}



