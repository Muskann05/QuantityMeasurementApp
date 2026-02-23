package com.quantity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                App.QuantityLength.convert(1.0,
                        App.LengthUnit.FEET,
                        App.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                App.QuantityLength.convert(24.0,
                        App.LengthUnit.INCHES,
                        App.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                App.QuantityLength.convert(1.0,
                        App.LengthUnit.YARDS,
                        App.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        assertEquals(2.0,
                App.QuantityLength.convert(72.0,
                        App.LengthUnit.INCHES,
                        App.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                App.QuantityLength.convert(2.54,
                        App.LengthUnit.CENTIMETERS,
                        App.LengthUnit.INCHES),
                1e-4);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double v = 5.0;

        double result =
                App.QuantityLength.convert(
                        App.QuantityLength.convert(v,
                                App.LengthUnit.FEET,
                                App.LengthUnit.YARDS),
                        App.LengthUnit.YARDS,
                        App.LengthUnit.FEET);

        assertEquals(v, result, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                App.QuantityLength.convert(0.0,
                        App.LengthUnit.FEET,
                        App.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                App.QuantityLength.convert(-1.0,
                        App.LengthUnit.FEET,
                        App.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                App.QuantityLength.convert(1.0, null,
                        App.LengthUnit.FEET));
    }

    @Test
    void testConversion_NaNOrInfinite_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                App.QuantityLength.convert(Double.NaN,
                        App.LengthUnit.FEET,
                        App.LengthUnit.INCHES));

        assertThrows(IllegalArgumentException.class, () ->
                App.QuantityLength.convert(Double.POSITIVE_INFINITY,
                        App.LengthUnit.FEET,
                        App.LengthUnit.INCHES));
    }

    @Test
    void testConversion_PrecisionTolerance() {
        double result =
                App.QuantityLength.convert(1.0,
                        App.LengthUnit.CENTIMETERS,
                        App.LengthUnit.FEET);

        assertEquals(0.0328084, result, 1e-6);
    }

    @Test
    void testEquality_YardToFeet() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARDS);
        App.QuantityLength q2 = new App.QuantityLength(3.0, App.LengthUnit.FEET);
        assertEquals(q1, q2);
    }
}

