/**
 * For the EP & BVA testing, I grouped them based on which test cases would yield the same results.
 * When I designed the test cases I set all booleans == false to focus solely on
 * the EP or BV. I figured that in the end with DT testing, all the combinations would be tested, so 
 * in order to limit the amount of duplicates tests, the booleans were false in EP and BVA testing. 
 * I couldn't come up with a better way to test the DT so each rule has it's own function. 
 */

package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class EnergyCalculatorTest {

    private EnergyCalculator calculator;

    @BeforeEach
    void newCalculator() {
        calculator = new EnergyCalculator();
    }

    // ----------------- EP | BVA ------------------------- //

    @ParameterizedTest(name = "EP/BVA Testing {index}: kwh={0} -> expected={3}")
    @CsvSource({
        // grouping EPs and BVs that produce the same results
        
        // EP1 | BV1, BV2
        "-1, false, false, INVALID",

        // EP2 | BV3-6 
        "1, false, false, 0.0",
        "250, false, false, 0.0",
        "500, false, false, 0.0",

        // EP3 | BV7-BV10 
        // DT testing should catch the other conditions 
        "501, false, false, 0.0",
        "1000, false, false, 0.0",
        "1500, false, false, 0.0",

        // EP4 | BV11-BV12
        // DT testing should catch the other conditions 
        "1501, false, false, 0.05",
        "2500, false, false, 0.05"
       
    })

    // function to test data above
    void epAndBVATests(double kwh, boolean hasSmartDevice, boolean peakOptOut, String expected) {
        if (expected.equals("INVALID")) {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculateRebate(
                kwh, hasSmartDevice, peakOptOut
            ));
        } else {
            double rebate = calculator.calculateRebate(kwh, hasSmartDevice, peakOptOut);
            assertEquals(Double.parseDouble(expected), rebate);        
        }
    }

    // ---------------- DT (one test per rule) ------------------ //

    // R1 (if kwh <= 0)
    @Test
    void invalidUsage() {
        assertThrows(IllegalArgumentException.class, () ->
    calculator.calculateRebate(-1, true, false));
    }

    // R2 (if kwh > 500 && <= 1500 && both booleans are true)
    @Test 
    void tierTwoSmartAndOptOut() {
        double rebate = calculator.calculateRebate(1000, true, true);
        assertEquals(0.15, rebate); 
    }

    // R3 (if kwh > 500 && <= 1500 && both booleans are false)
    @Test
    void tierTwoNeither() {
        double rebate = calculator.calculateRebate(1000, false, false);
        assertEquals(0.0, rebate);
    }

    // R4 (if kwh > 1500 && both booleans are true)
    @Test 
    void tierThreeSmartAndOptOut() {
        double rebate = calculator.calculateRebate(2500, true, true);
        assertEquals(0.20, rebate);
    }

    // R5 (if kwh > 1500 && one boolean is true)
    @Test
    void tierThreeSmartOrOptOut() {
        double rebate = calculator.calculateRebate(2500, false, true);
        assertEquals(0.05, rebate);
    }

    // R6 (if kwh > 0 && <= 500 && one boolean is true)
    @Test
    void tierOneSmart() {
        double rebate = calculator.calculateRebate(250, true, false);
        assertEquals(0.0, rebate); 
    }

    // R7 (if kwh > 500 && <= 1500 && one boolean is true)
    @Test
    void tierTwoSmartOrOptOut() {
        double rebate = calculator.calculateRebate(1000, true, false);
        assertEquals(0.10, rebate);

    }

    


}
