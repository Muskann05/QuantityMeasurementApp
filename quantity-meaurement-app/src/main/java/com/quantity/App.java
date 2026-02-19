package com.quantity;

public class App {

    // Enum for supported units
    public enum LengthUnit {
        FEET(1.0),                 // base unit: feet
        INCH(1.0 / 12),            // 1 inch = 1/12 feet
        YARD(3.0),                 // 1 yard = 3 feet
        CENTIMETER((0.393701 / 12)); // 1 cm = 0.393701 inch = 0.393701/12 feet

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

    public static void main(String[] args) {
        System.out.println("=== UC4 Extended Unit Demonstration ===\n");

        // Basic yard and centimeter comparisons
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feet1 = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inch1 = new QuantityLength(36.0, LengthUnit.INCH);
        QuantityLength cm1 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength inchFromCm = new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println("Yard to Feet equivalent: " + yard1.equals(feet1));        // true
        System.out.println("Yard to Inch equivalent: " + yard1.equals(inch1));        // true
        System.out.println("Centimeter to Inch equivalent: " + cm1.equals(inchFromCm)); // true

        // Same-unit checks
        QuantityLength yard2 = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength yard3 = new QuantityLength(2.0, LengthUnit.YARD);
        System.out.println("Yard to Yard same value: " + yard2.equals(yard3));        // true

        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        QuantityLength cm3 = new QuantityLength(2.0, LengthUnit.CENTIMETER);
        System.out.println("Centimeter to Centimeter same value: " + cm2.equals(cm3)); // true

        // Different value checks
        QuantityLength yardDiff = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength feetDiff = new QuantityLength(2.0, LengthUnit.FEET);
        System.out.println("Yard to Feet different value: " + yardDiff.equals(feetDiff)); // false

        QuantityLength cmDiff = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength feetDiff2 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("Centimeter to Feet different value: " + cmDiff.equals(feetDiff2)); // false

        // Transitive property
        QuantityLength yardTrans = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength feetTrans = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inchTrans = new QuantityLength(72.0, LengthUnit.INCH);
        boolean transitive = yardTrans.equals(feetTrans) && feetTrans.equals(inchTrans);
        System.out.println("Multi-unit transitive property: " + transitive); // true

        // Null and same reference
        System.out.println("Yard same reference: " + yard1.equals(yard1));
        System.out.println("Yard null comparison: " + yard1.equals(null));
        System.out.println("Centimeter same reference: " + cm1.equals(cm1));
        System.out.println("Centimeter null comparison: " + cm1.equals(null));

        // Invalid or NaN (commented to avoid crash)
        // QuantityLength invalid = new QuantityLength(1.0, null);
        // QuantityLength nanValue = new QuantityLength(Double.NaN, LengthUnit.FEET);

        System.out.println("\nUC4 Demonstration Complete");
    }
}