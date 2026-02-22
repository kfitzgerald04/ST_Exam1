// Fault 2 Missed by EQs, but caught by BVA 

package com.fitzgerald;

public class Fault2 {

    public double calculateRebate(double kwh, boolean hasSmartDevice, boolean peakOptOut) {
        if (kwh <= 0) {
            throw new IllegalArgumentException("Usage must be positive.");
        }

        double rebatePercent = 0.0;

        // FAULT: changed <= 1500 to < 1500 meaning that 1500 now falls out completely of this range
        // making it no longer a valid boundary, and instead will fall under kwh >= 1500 
        // EP won't catch it because it only tests a value in the center of the EP
        if (kwh > 500 && kwh < 1500) {
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.15;
            } else if (hasSmartDevice || peakOptOut) {
                rebatePercent = 0.10;
            }
        } else if (kwh >= 1500) { // FAULT ^^^
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.20;
            } else {
                rebatePercent = 0.05;
            }
        }

        return rebatePercent;
    }
}



