package edu.depaul.se422.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.stream.Stream;

public class TotalCostCalculatorTest {

    private TotalCostCalculator calculator;

    @BeforeEach
    void setup(){

        calculator = new TotalCostCalculator();

    }

    @Test
    @DisplayName("Boundary testing for standard shipping...")
    void boundaryTesting(){

        assertAll(
                () -> {
                    assertEquals(20.00, calculator.calculate(10.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    assertEquals(60.00, calculator.calculate(50.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    assertEquals(51.00, calculator.calculate(51.00, "OH", ShippingType.STANDARD));
                },
                () -> {
                    assertEquals(45.00, calculator.calculate(20, "OH", ShippingType.NEXT_DAY));
                },
                () -> {
                    assertEquals(75.00, calculator.calculate(50.00, "OH", ShippingType.NEXT_DAY));
                },
                () -> {
                    assertEquals(76.00, calculator.calculate(51.00, "OH", ShippingType.NEXT_DAY));
                }
        );

    }

    @ParameterizedTest
    @MethodSource("provideWeakArguments")
    @DisplayName("This function allows us to test weak normal and weak robust cases")
    void weakTesting(String state, ShippingType type, double rawCost, boolean validArgs){

        if(validArgs){
            double expectedPrice = rawCost;
            if(state.equals("CA") || state.equals("IL") || state.equals("NY")){
                expectedPrice = expectedPrice + (expectedPrice * 0.06);
            }
            if(type == ShippingType.NEXT_DAY){
                expectedPrice += 25.00;
            }else if(type == ShippingType.STANDARD && rawCost <= 50.00){
                expectedPrice += 10.00;
            }

            assertEquals(expectedPrice, calculator.calculate(rawCost, state, type));
        }else{
            assertThrows(IllegalArgumentException.class, () -> calculator.calculate(rawCost, state, type));
        }


    }

    private static Stream<Arguments> provideWeakArguments(){
        return Stream.of(

                //Weak Normal
                Arguments.of("CA", ShippingType.STANDARD, 10.00, true),
                Arguments.of("NY", ShippingType.STANDARD, 55.00, true),

                //Weak Robust
                Arguments.of("NY", ShippingType.STANDARD, 0.00, false),
                Arguments.of("CA", ShippingType.NEXT_DAY, 0.00, false),
                Arguments.of("OH", ShippingType.NEXT_DAY, 0.00, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStrongArguments")
    @DisplayName("This function allows us to test weak normal and weak robust cases")
    void strongTesting(String state, ShippingType type, double rawCost, boolean validArgs){

        if(validArgs){
            double expectedPrice = rawCost;
            if(state.equals("CA") || state.equals("IL") || state.equals("NY")){
                expectedPrice = expectedPrice + (expectedPrice * 0.06);
            }
            if(type.equals(ShippingType.NEXT_DAY)){
                expectedPrice += 25.00;
            }else if(type.equals(ShippingType.STANDARD) && rawCost <= 50.00){
                expectedPrice += 10.00;
            }

            assertEquals(expectedPrice, calculator.calculate(rawCost, state, type));
        }else{
            assertThrows(IllegalArgumentException.class, () -> calculator.calculate(rawCost, state, type));
        }


    }

    private static Stream<Arguments> provideStrongArguments(){
        return Stream.of(

                //Strong normal
                Arguments.of("CA", ShippingType.STANDARD, 10.00, true),
                Arguments.of("NY", ShippingType.STANDARD, 10.00, true),
                Arguments.of("IL", ShippingType.STANDARD, 10.00, true),
                Arguments.of("OH", ShippingType.STANDARD, 10.00, true),
                Arguments.of("CA", ShippingType.NEXT_DAY, 10.00, true),
                Arguments.of("NY", ShippingType.NEXT_DAY, 10.00, true),
                Arguments.of("IL", ShippingType.NEXT_DAY, 10.00, true),
                Arguments.of("OH", ShippingType.NEXT_DAY, 10.00, true),
                Arguments.of("CA", ShippingType.STANDARD, 55.00, true),
                Arguments.of("NY", ShippingType.STANDARD, 55.00, true),
                Arguments.of("IL", ShippingType.STANDARD, 55.00, true),
                Arguments.of("OH", ShippingType.STANDARD, 55.00, true),
                Arguments.of("CA", ShippingType.NEXT_DAY, 55.00, true),
                Arguments.of("NY", ShippingType.NEXT_DAY, 55.00, true),
                Arguments.of("IL", ShippingType.NEXT_DAY, 55.00, true),
                Arguments.of("OH", ShippingType.NEXT_DAY, 55.00, true),

                //Strong Robust
                Arguments.of("CA", ShippingType.STANDARD, 0, false),
                Arguments.of("NY", ShippingType.STANDARD, 0, false),
                Arguments.of("IL", ShippingType.STANDARD, 0, false),
                Arguments.of("OH", ShippingType.STANDARD, 0, false),
                Arguments.of("CA", ShippingType.NEXT_DAY, 0, false),
                Arguments.of("NY", ShippingType.NEXT_DAY, 0, false),
                Arguments.of("IL", ShippingType.NEXT_DAY, 0, false),
                Arguments.of("OH", ShippingType.NEXT_DAY, 0, false),

                Arguments.of("", ShippingType.STANDARD, 10.0, false),
                Arguments.of("", ShippingType.NEXT_DAY, 10.0, false)

        );
    }



}
