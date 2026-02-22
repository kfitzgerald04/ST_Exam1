// Fault 4 Missed by Decision Tables, but caught by Code Coverage

package com.fitzgerald;

public class Fault4 {

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
            if (hasSmartDevice && peakOptOut) {
                rebatePercent = 0.20;
            } else {
                rebatePercent = 0.05;
                rebatePercent = 0.90;    // FAULT: this line overwrites the 0.05, but there isn't anything that actually validates it
                                        // DT wont' catch it because it's not checking for this condtion 
            }
        }

        return rebatePercent;
    }
}



