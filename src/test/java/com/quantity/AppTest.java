package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private static final double EPSILON = 0.001;

    // 1
    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // 2
    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // 3
    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 4
    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    // 5
    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // 6
    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // 7
    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength r1 = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        QuantityLength r2 = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.FEET),
                LengthUnit.YARDS);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    // 8
    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }

    // 9
    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    // 10
    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        null));
    }

    // 11
    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1000.0, LengthUnit.FEET),
                new QuantityLength(500.0, LengthUnit.FEET),
                LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    // 12
    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }

    // 13
    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {

        LengthUnit[] units = LengthUnit.values();

        for (LengthUnit u1 : units) {
            for (LengthUnit u2 : units) {
                for (LengthUnit target : units) {

                    QuantityLength q1 = new QuantityLength(2.0, u1);
                    QuantityLength q2 = new QuantityLength(3.0, u2);

                    QuantityLength result =
                            QuantityLength.add(q1, q2, target);

                    assertNotNull(result);
                    assertEquals(target, result.getUnit());
                }
            }
        }
    }

    // 14
    @Test
    void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
    }
}