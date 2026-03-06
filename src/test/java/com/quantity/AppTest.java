package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    // Interface Implementation
    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        assertNotNull(LengthUnit.FEET.getConversionFactor());
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        assertNotNull(WeightUnit.KILOGRAM.getConversionFactor());
    }
    // Equality Tests

    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    // Conversion Tests

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.01);
    }

    // Addition Tests

    @Test
    void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // Cross Category Safety

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // Constructor Validation

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }
    
    // HashCode Contract

    @Test
    void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1.hashCode(), q2.hashCode());
    }

    // Equals Contract

    @Test
    void testEquals_GenericQuantity_ContractPreservation() {

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> c = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    // Volume Unit Tests (UC11)

    @Test
    void testVolumeEquality() {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testVolumeConversion() {

        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), 0.01);
    }

    @Test
    void testVolumeAddition() {

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2, VolumeUnit.LITRE);

        assertEquals(2.0, result.getValue(), 0.01);
    }

}