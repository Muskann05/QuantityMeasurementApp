package com.quantity;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    
    // IMeasurable Interface Tests

    @Test
    void testIMeasurableInterface_LengthUnitImplementation() {
        LengthUnit unit = LengthUnit.FEET;

        assertNotNull(unit.getConversionFactor());
        assertEquals(1.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1.0));
        assertEquals("FEET", unit.getUnitName());
    }

    @Test
    void testIMeasurableInterface_WeightUnitImplementation() {
        WeightUnit unit = WeightUnit.KILOGRAM;

        assertNotNull(unit.getConversionFactor());
        assertEquals(1.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1.0));
        assertEquals("KILOGRAM", unit.getUnitName());
    }

    @Test
    void testIMeasurableInterface_ConsistentBehavior() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
    }

    
    // Generic Equality

    @Test
    void testGenericQuantity_LengthOperations_Equality() {
        assertEquals(
                new Quantity<>(1.0, LengthUnit.FEET),
                new Quantity<>(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testGenericQuantity_WeightOperations_Equality() {
        assertEquals(
                new Quantity<>(1.0, WeightUnit.KILOGRAM),
                new Quantity<>(1000.0, WeightUnit.GRAM)
        );
    }

    
    // Conversion

    @Test
    void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue());
    }

    @Test
    void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue());
    }

    
    // Addition

    @Test
    void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> result =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.FEET);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> result =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue());
    }

    // Cross Category

    @Test
    void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // Constructor Validation

    @Test
    void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // All Unit Combination Conversion

    @Test
    void testGenericQuantity_Conversion_AllUnitCombinations() {
        for (LengthUnit from : LengthUnit.values()) {
            for (LengthUnit to : LengthUnit.values()) {
                Quantity<LengthUnit> q =
                        new Quantity<>(10.0, from).convertTo(to);
                assertNotNull(q);
            }
        }
    }

    // Equality Contract

    @Test
    void testEquals_GenericQuantity_ContractPreservation() {

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> c = new Quantity<>(1.0, LengthUnit.FEET);

        // Reflexive
        assertEquals(a, a);

        // Symmetric
        assertEquals(a, b);
        assertEquals(b, a);

        // Transitive
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    @Test
    void testHashCode_GenericQuantity_Consistency() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    
    // Immutability

    @Test
    void testImmutability_GenericQuantity() {
        assertTrue(Modifier.isFinal(Quantity.class.getModifiers()));
    }

    // Wildcard Test

    private void wildcardMethod(Quantity<?> q) {
        assertNotNull(q);
    }

    @Test
    void testTypeWildcard_FlexibleSignatures() {
        wildcardMethod(new Quantity<>(1.0, LengthUnit.FEET));
        wildcardMethod(new Quantity<>(1.0, WeightUnit.KILOGRAM));
    }

    // Scalability Test - New Enum

    enum VolumeUnit implements IMeasurable {
        LITER(1.0),
        MILLILITER(0.001);

        private final double factor;
        VolumeUnit(double factor) { this.factor = factor; }

        public double getConversionFactor() { return factor; }
        public double convertToBaseUnit(double value) { return value * factor; }
        public double convertFromBaseUnit(double base) { return base / factor; }
        public String getUnitName() { return name(); }
    }

    @Test
    void testScalability_NewUnitEnumIntegration() {
        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITER);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITER);

        assertEquals(q1, q2);
    }

    // Bounded Type Enforcement (Compile-time concept)

    @Test
    void testGenericBoundedTypeParameter_Enforcement() {
        assertTrue(IMeasurable.class.isAssignableFrom(LengthUnit.class));
    }

    // Immutability Check

    @Test
    void testNoSetterMethods() {
        assertEquals(0,
                java.util.Arrays.stream(Quantity.class.getDeclaredMethods())
                        .filter(m -> m.getName().startsWith("set"))
                        .count());
    }
}