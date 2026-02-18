package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    // FEET TESTS

    // Test same value equality
    @Test
    void testFeetEquality_SameValue() {

        boolean result = App.compareFeet(1.0, 1.0);

        assertTrue(result);
    }


    // Test different values inequality
    @Test
    void testFeetEquality_DifferentValue() {

        boolean result = App.compareFeet(1.0, 2.0);

        assertFalse(result);
    }


    // Test same reference (reflexive property)
    @Test
    void testFeetEquality_SameReference() {

        App.Feet feet =
                new App.Feet(1.0);

        assertTrue(feet.equals(feet));
    }


    // Test null comparison
    @Test
    void testFeetEquality_NullComparison() {

        App.Feet feet =
                new App.Feet(1.0);

        assertFalse(feet.equals(null));
    }


    // Test type safety
    @Test
    void testFeetEquality_NonNumericInput() {

        App.Feet feet =
                new App.Feet(1.0);

        assertFalse(feet.equals("abc"));
    }


    //  INCHES TESTS 

    // Test same value equality
    @Test
    void testInchesEquality_SameValue() {

        boolean result = App.compareInches(1.0, 1.0);

        assertTrue(result);
    }


    // Test different value inequality
    @Test
    void testInchesEquality_DifferentValue() {

        boolean result = App.compareInches(1.0, 2.0);

        assertFalse(result);
    }


    // Test same reference
    @Test
    void testInchesEquality_SameReference() {

        App.Inches inches =
                new App.Inches(1.0);

        assertTrue(inches.equals(inches));
    }


    // Test null comparison
    @Test
    void testInchesEquality_NullComparison() {

        App.Inches inches =
                new App.Inches(1.0);

        assertFalse(inches.equals(null));
    }


    // Test type safety
    @Test
    void testInchesEquality_NonNumericInput() {

        App.Inches inches =
                new App.Inches(1.0);

        assertFalse(inches.equals("xyz"));
    }

}