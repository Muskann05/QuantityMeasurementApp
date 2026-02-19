package com.quantity;

public class App {

    // Enum for supported units
    public enum LengthUnit {
        FEET(1.0),       // Base unit: feet
        INCH(1.0 / 12);  // 1 inch = 1/12 feet

        private final double toFeetConversion;

        LengthUnit(double toFeetConversion) {
            this.toFeetConversion = toFeetConversion;
        }

        public double toFeet(double value) {
            return value * toFeetConversion;
        }
    }

    // Generic QuantityLength class
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit.name().toLowerCase();
        }
    }

    // Main method to demonstrate all test cases
    public static void main(String[] args) {

        System.out.println("=== UC3 QuantityLength Demonstration ===\n");

        // 1. Feet to Feet same value
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Feet to Feet same value: " + feet1.equals(feet2));

        // 2. Inch to Inch same value
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCH);
        System.out.println("Inch to Inch same value: " + inch1.equals(inch2));

        // 3. Cross-unit equality: 1 foot = 12 inches
        QuantityLength feet3 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch3 = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println("Feet to Inch equivalent: " + feet3.equals(inch3));

        // 4. Inch to Feet equivalent (symmetry)
        QuantityLength inch4 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength feet4 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Inch to Feet equivalent: " + inch4.equals(feet4));

        // 5. Feet to Feet different value
        QuantityLength feet5 = new QuantityLength(2.0, LengthUnit.FEET);
        System.out.println("Feet to Feet different value: " + feet1.equals(feet5));

        // 6. Inch to Inch different value
        QuantityLength inch5 = new QuantityLength(2.0, LengthUnit.INCH);
        System.out.println("Inch to Inch different value: " + inch1.equals(inch5));

        // 7. Same reference
        System.out.println("Same reference check: " + feet1.equals(feet1));

        // 8. Null comparison
        System.out.println("Null comparison: " + feet1.equals(null));

        // 9. Invalid unit example (commented to prevent runtime error)
        // QuantityLength invalid = new QuantityLength(1.0, null);

        // 10. NaN value example (commented to prevent runtime error)
        // QuantityLength nanValue = new QuantityLength(Double.NaN, LengthUnit.FEET);

        System.out.println("\nDemonstration Complete");
    }
}
