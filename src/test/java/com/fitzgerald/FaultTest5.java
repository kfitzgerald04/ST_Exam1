// Fault 5 Missed by Code Coverage, but caught by Branch Coverage

package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FaultTest5 {

    private Fault5 calculator;

    @BeforeEach
    void setUp() {
    calculator = new Fault5();
    }

    @ParameterizedTest(name = "EP/BVA Testing {index}: kwh={0} -> expected={3}")
    @CsvSource({
        
        // should fail due to if (hasSmartDevice || peakOptOut) instead of if (hasSmartDevice && peakOptOut)
        "1000, false, true, 0.0"
    })


    // ^^ this is a branch that never gets tested 

    void Fault5(double kwh, boolean hasSmartDevice, boolean peakOptOut, String expected) {
        if (expected.equals("INVALID")) {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculateRebate(
                kwh, hasSmartDevice, peakOptOut
            ));
        } else {
            double rebate = calculator.calculateRebate(kwh, hasSmartDevice, peakOptOut);
            assertEquals(Double.parseDouble(expected), rebate);        
        }
    }

}
