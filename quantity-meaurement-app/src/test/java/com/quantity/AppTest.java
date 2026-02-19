package com.quantity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.INCH);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_NullComparison_CrossUnit() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        App.QuantityLength q2 = new App.QuantityLength(12.0, App.LengthUnit.INCH);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        App.QuantityLength q1 = new App.QuantityLength(12.0, App.LengthUnit.INCH);
        App.QuantityLength q2 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        assertEquals(q1, q2);
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        App.QuantityLength q2 = new App.QuantityLength(2.0, App.LengthUnit.FEET);
        assertNotEquals(q1, q2);
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        App.QuantityLength q1 = new App.QuantityLength(1.0, App.LengthUnit.INCH);
        App.QuantityLength q2 = new App.QuantityLength(2.0, App.LengthUnit.INCH);
        assertNotEquals(q1, q2);
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
            new App.QuantityLength(1.0, null));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
            new App.QuantityLength(2.0, null));
    }

    @Test
    void testEquality_SameReference() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    void testEquality_NullComparison() {
        App.QuantityLength q = new App.QuantityLength(1.0, App.LengthUnit.FEET);
        assertNotEquals(q, null);
    }
}