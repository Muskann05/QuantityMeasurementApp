package com.quantity;

public class App {

    /**
     * LengthUnit enum
     * Conversion factors relative to base unit: FEET
     */
    public enum LengthUnit {

        FEET(1.0),                     // Base unit
        INCHES(1.0 / 12.0),            // 1 inch = 1/12 feet
        YARDS(3.0),                    // 1 yard = 3 feet
        CENTIMETERS(0.393701 / 12.0);  // 1 cm = 0.393701 inches

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    /**
     * Immutable Value Object representing a length.
     */
    public static final class QuantityLength {

        private static final double EPSILON = 1e-6;

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            validate(value, unit);
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        /**
         * Static Conversion API
         * Formula:
         * result = value × (sourceFactor / targetFactor)
         */
        public static double convert(double value,
                                     LengthUnit source,
                                     LengthUnit target) {

            validate(value, source);

            if (target == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            if (source == target)
                return value;

            double valueInFeet = source.toFeet(value);
            return target.fromFeet(valueInFeet);
        }

        /**
         * Instance conversion method.
         * Returns new immutable object.
         */
        public QuantityLength convertTo(LengthUnit target) {
            double convertedValue = convert(this.value, this.unit, target);
            return new QuantityLength(convertedValue, target);
        }

        private static void validate(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");
        }

        private double toBaseFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(this.toBaseFeet() - other.toBaseFeet()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.valueOf(toBaseFeet()).hashCode();
        }
        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
    
       // METHOD OVERLOADING DEMONSTRATION       
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);
        System.out.println("convert(" + value + ", " + from + ", " + to + ") = " + result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity,
                                                   LengthUnit to) {

        QuantityLength result = quantity.convertTo(to);
        System.out.println(quantity + " -> " + result);
    }

    public static void demonstrateLengthEquality(QuantityLength q1,
                                                 QuantityLength q2) {

        System.out.println(q1 + " equals " + q2 + " ? " + q1.equals(q2));
    }
   
       // MAIN METHOD
      
    public static void main(String[] args) {

        System.out.println("UC1–UC5 Quantity Measurement App\n");

        // Basic Conversions
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);

        // Zero & Negative
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES);

        // Instance Method Conversion
        QuantityLength lengthInYards = new QuantityLength(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);

        // Equality Demonstration
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);
        demonstrateLengthEquality(q1, q2);

        // Transitive Equality
        QuantityLength q3 = new QuantityLength(36.0, LengthUnit.INCHES);
        demonstrateLengthEquality(q2, q3);

        System.out.println("\nEnd");
    }
}