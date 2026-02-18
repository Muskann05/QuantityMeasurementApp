package com.quantity;

public class App {

    // Inner class representing Feet measurement
    public static class Feet {

        private final double value;   // immutable value

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        // Override equals method
        @Override
        public boolean equals(Object obj) {

            // 1. Same reference check (Reflexive)
            if (this == obj) {
                return true;
            }

            // 2. Null check
            if (obj == null) {
                return false;
            }

            // 3. Type check
            if (getClass() != obj.getClass()) {
                return false;
            }

            // 4. Cast safely
            Feet other = (Feet) obj;

            // 5. Compare using Double.compare (NOT ==)
            return Double.compare(this.value, other.value) == 0;
        }

        // Good practice: override hashCode whenever equals is overridden
        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Main method to test manually
    public static void main(String[] args) {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        System.out.println("Are both measurements equal? " + first.equals(second));
    }
}