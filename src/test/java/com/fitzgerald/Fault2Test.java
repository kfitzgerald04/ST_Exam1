// Fault 2 Missed by EQs, but caught by BVA 

package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Fault2Test {

    private Fault2 calculator;

    @BeforeEach
    void setUp() {
    calculator = new Fault2();
    }

    @ParameterizedTest(name = "EP/BVA Testing {index}: kwh={0} -> expected={3}")
    @CsvSource({
        
        // should fail due to (kwh < 1500 ) instead of (kwh <= 1500)
         "1500, false, false, 0.0"
    })


    // expected == 0.0, but the inserted fault == 0.05 becauuse of the BV 1500

    void Fault2(double kwh, boolean hasSmartDevice, boolean peakOptOut, String expected) {
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
