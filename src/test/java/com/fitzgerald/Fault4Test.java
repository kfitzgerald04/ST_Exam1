// Fault 4 Missed by Decision Tables, but caught by Code Coverage

package com.fitzgerald;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Fault4Test {

    private Fault4 calculator;

    @BeforeEach
    void setUp() {
        calculator = new Fault4();
    }

    // test will pass, but code coverage is overwritten
    @Test
    void Fault4() {
        double rebate = calculator.calculateRebate(2500, false, true);

        // is technically still correct
        assertEquals(0.07, rebate);
    }
}