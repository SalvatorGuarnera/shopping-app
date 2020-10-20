package edu.depaul.se422.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TotalCostCalculatorTest {

    @Test
    @DisplayName("Testing total cost calculator functionality")
    void testGetShippingCost(){

        TotalCostCalculator calculator = new TotalCostCalculator();

        assertAll("Boundary testing for shipping test",
                () -> {
                    System.out.println("Below 50 dollars, tax-free state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(10.00, "OH", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("Below 50 dollars, taxed state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(10.00, "CA", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("Below 50 dollars, tax-free state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(10.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    System.out.println("Below 50 dollars, taxed state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(10.00, "NY", ShippingType.STANDARD));
                },
                () -> {
                    System.out.println("50 dollars, tax-free state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(50.00, "OH", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("50 dollars, taxed state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(50.00, "CA", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("50 dollars, tax-free state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(50.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    System.out.println("50 dollars, taxed state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(50.00, "NY", ShippingType.STANDARD));
                },
                () -> {
                    System.out.println("Above 50 dollars, tax-free state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(51.00, "OH", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("Above 50 dollars, taxed state, next day shipping...");
                    assertEquals(100.00, calculator.calculate(51.00, "CA", ShippingType.NEXT_DAY));
                },
                () -> {
                    System.out.println("Above 50 dollars, tax-free state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(51.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    System.out.println("Above 50 dollars, taxed state, standard shipping...");
                    assertEquals(100.00, calculator.calculate(51.00, "NY", ShippingType.STANDARD));
                }
        );

    }

}
