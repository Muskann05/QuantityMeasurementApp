package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    // ENUM COMPUTATION TESTS

    @Test
    void testArithmeticOperation_Add_EnumComputation() {
        assertEquals(15.0, ArithmeticOperation.ADD.compute(10, 5));
    }

    @Test
    void testArithmeticOperation_Subtract_EnumComputation() {
        assertEquals(5.0, ArithmeticOperation.SUBTRACT.compute(10, 5));
    }

    @Test
    void testArithmeticOperation_Divide_EnumComputation() {
        assertEquals(2.0, ArithmeticOperation.DIVIDE.compute(10, 5));
    }

    @Test
    void testArithmeticOperation_DivideByZero_EnumThrows() {
        assertThrows(ArithmeticException.class,
                () -> ArithmeticOperation.DIVIDE.compute(10, 0));
    }

    // VALIDATION TESTS

    @Test
    void testValidation_NullOperand_Add() {

        Quantity<LengthUnit> q =
                new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.add(null));
    }

    @Test
    void testValidation_NullOperand_Subtract() {

        Quantity<LengthUnit> q =
                new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.subtract(null));
    }

    @Test
    void testValidation_NullOperand_Divide() {

        Quantity<LengthUnit> q =
                new Quantity<>(10, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q.divide(null));
    }

    @Test
    void testValidation_CrossCategory() {

        Quantity<LengthUnit> length =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(5, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class,
                () -> length.add((Quantity) weight));
    }

    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(5, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(q2, null));
    }

    // ADDITION TESTS

    @Test
    void testAdd_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue());
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitTargetUnit_Add() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(10, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(5000, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                q1.add(q2, WeightUnit.GRAM);

        assertEquals(15000.0, result.getValue());
    }

    // SUBTRACTION TESTS

    @Test
    void testSubtract_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue());
    }

    @Test
    void testExplicitTargetUnit_Subtract() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(5, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(2, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                q1.subtract(q2, VolumeUnit.MILLILITRE);

        assertEquals(3000.0, result.getValue());
    }

    // DIVISION TESTS

    @Test
    void testDivide_UC12_BehaviorPreserved() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(5.0, result);
    }

    @Test
    void testDivide_CrossUnits() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(24, LengthUnit.INCHES);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result);
    }

    // ROUNDING TEST

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.2345, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0004, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(1.23, result.getValue());
    }

    // IMMUTABILITY TESTS

    @Test
    void testImmutability_AfterAdd() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        q1.add(q2);

        assertEquals(10, q1.getValue());
    }

    @Test
    void testImmutability_AfterSubtract() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(10, q1.getValue());
    }

    @Test
    void testImmutability_AfterDivide() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(10, q1.getValue());
    }
}