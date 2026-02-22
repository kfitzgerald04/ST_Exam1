// Fault 3 Missed by BVA, but caught by Decision Table

package com.fitzgerald;

public class Fault3 {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
    
        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        // FAULT: changed the first && condition to an || condition
        // the DT should catch it because the the expected results should yield 0.10, but one of the cases will have an actual of 0.15
        // BVA won't catch it because i kept all my boolean values nominal for it so it's only checking the BV itself
        if (kwh > 500 && kwh <= 1500) {
            if (hasSmartDevice || peakOptOut) {
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



