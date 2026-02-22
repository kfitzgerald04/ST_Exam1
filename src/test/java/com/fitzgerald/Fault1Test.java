// Fault 1 Caught by Equivalence Partitions 
package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Fault1Test {

    private Fault1 calculator;

    @BeforeEach
    void setUp() {
    calculator = new Fault1();
    }

    @ParameterizedTest(name = "EP/BVA Testing {index}: kwh={0} -> expected={3}")
    @CsvSource({
        
        // should fail due to (kwh < 0) instead of (kwh <= 0)
        "0, false, false, INVALID"
    })


    // expected == INVALID, but the inserted fault == 0.0

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
