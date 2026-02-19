package com.quantity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class AppTest {

    // Yard to Yard
    @Test
    void testEquality_YardToYard_SameValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        App.QuantityLength q2 = new App.QuantityLength(2.0, App.LengthUnit.YARD);
        assertNotEquals(q1, q2);
    }

    // Yard to Feet / Feet to Yard
    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        App.QuantityLength q2 = new App.QuantityLength(3.0, App.LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(3.0, App.LengthUnit.FEET);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        assertEquals(q1, q2);
    }

    // Yard to Inches / Inches to Yard
    @Test
    void testEquality_YardToInches_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        App.QuantityLength q2 = new App.QuantityLength(36.0, App.LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(36.0, App.LengthUnit.INCH);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        assertEquals(q1, q2);
    }

    // Yard to Feet non-equivalent
    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        App.QuantityLength q2 = new App.QuantityLength(2.0, App.LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    // Centimeter to Inches
    @Test
    void testEquality_centimetersToInches_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.CENTIMETER);
        App.QuantityLength q2 = new App.QuantityLength(0.393701, App.LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    // Centimeter to Feet non-equivalent
    @Test
    void testEquality_centimetersToFeet_NonEquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.CENTIMETER);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    // Transitive property multi-unit
    @Test
    void testEquality_AllUnits_ComplexScenario() {
        App.QuantityLength yard = new App.QuantityLength(2.0, App.LengthUnit.YARD);
        App.QuantityLength feet = new App.QuantityLength(6.0, App.LengthUnit.FEET);
        App.QuantityLength inch = new App.QuantityLength(72.0, App.LengthUnit.INCH);
        assertTrue(yard.equals(feet) && feet.equals(inch));
    }

    // Null and reference checks for yards
    @Test
    void testEquality_YardWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
            new App.QuantityLength(1.0, null));
    }

    @Test
    void testEquality_YardSameReference() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        assertEquals(q, q);
    }

    @Test
    void testEquality_YardNullComparison() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.YARD);
        assertNotEquals(q, null);
    }

    // Null and reference checks for centimeters
    @Test
    void testEquality_CentimetersWithNullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
            new App.QuantityLength(1.0, null));
    }

    @Test
    void testEquality_CentimetersSameReference() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.CENTIMETER);
        assertEquals(q, q);
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.CENTIMETER);
        assertNotEquals(q, null);
    }
}