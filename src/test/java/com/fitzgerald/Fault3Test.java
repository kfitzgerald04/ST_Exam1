// Fault 3 Missed by BVA, but caught by Decision Table

package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Fault3Test {

    private Fault3 calculator;

    @BeforeEach
    void setUp() {
    calculator = new Fault3();
    }

    @ParameterizedTest(name = "EP/BVA Testing {index}: kwh={0} -> expected={3}")
    @CsvSource({
        
        // should fail due to if (hasSmartDevice || peakOptOut) instead of if (hasSmartDevice && peakOptOut)
        "1000, false, true, 0.0"
    })


    // expected == 0.0, but the inserted fault == 0.15

    void Fault1(double kwh, boolean hasSmartDevice, boolean peakOptOut, String expected) {
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
