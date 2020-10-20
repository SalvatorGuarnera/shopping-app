package edu.depaul.se422.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.se433.shoppingapp.TaxCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaxCalculatorTest {

    @Test
    @DisplayName("Tests that taxes are calculated properly with found state values (CA, IL, NY)")
    void testHappyPathTaxes(){

        TaxCalculator taxCalculator = new TaxCalculator();
        double expectedValue = (99.99) * 0.06;

        assertAll("Testing valid tax calculations",
                () -> {
                    assertEquals(expectedValue, taxCalculator.calculate(99.99, "CA"));
                },
                () -> {
                    assertEquals(expectedValue, taxCalculator.calculate(99.99, "IL"));
                },
                () -> {
                    assertEquals(expectedValue, taxCalculator.calculate(99.99, "NY"));
                }
        );
    }

    @Test
    @DisplayName("Tests that taxes are calculated properly with states other than (CA, IL, NY")
    void testNegativePathTaxes(){

        TaxCalculator taxCalculator = new TaxCalculator();

        assertAll("Testing invalid tax calculations",
                () -> {
                    assertEquals(0.0, taxCalculator.calculate(99.99, "OH"));
                },
                () -> {
                    assertEquals(0.0, taxCalculator.calculate(19.99, "WA"));
                },
                () -> {
                    assertEquals(0.0, taxCalculator.calculate(29.99, "FL"));
                }
        );

    }

}
