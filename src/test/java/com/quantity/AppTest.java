package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private static final double EPSILON = 0.0001;

    /*UC5 CONVERSION TESTS*/

    @Test
    void testFeetToInches() {
        assertEquals(12,
                Quantity.convert(1,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2,
                Quantity.convert(24,
                        LengthUnit.INCHES,
                        LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testYardsToFeet() {
        assertEquals(9,
                Quantity.convert(3,
                        LengthUnit.YARDS,
                        LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testZeroConversion() {
        assertEquals(0,
                Quantity.convert(0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> Quantity.convert(
                        Double.NaN,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    /* UC6 ADDITION TESTS */

    @Test
    void testSameUnitAddition() {

        Quantity q1 =
                new Quantity(1, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(2, LengthUnit.FEET);

        assertEquals(3,
                q1.add(q2).getValue(),
                EPSILON);
    }

    @Test
    void testFeetPlusInches() {

        Quantity q1 =
                new Quantity(1, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(12, LengthUnit.INCHES);

        assertEquals(2,
                q1.add(q2).getValue(),
                EPSILON);
    }

    @Test
    void testAdditionWithZero() {

        Quantity q1 =
                new Quantity(5, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(0, LengthUnit.INCHES);

        assertEquals(5,
                q1.add(q2).getValue(),
                EPSILON);
    }

    @Test
    void testNegativeAddition() {

        Quantity q1 =
                new Quantity(5, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(-2, LengthUnit.FEET);

        assertEquals(3,
                q1.add(q2).getValue(),
                EPSILON);
    }

    @Test
    void testNullAddition() {

        Quantity q =
                new Quantity(1, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q.add(null));
    }
}