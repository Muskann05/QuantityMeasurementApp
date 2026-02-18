package com.quantity;

public class App {
	    // FEET CLASS
	    public static class Feet {

	        private final double value;

	        public Feet(double value) {
	            this.value = value;
	        }

	        public double getValue() {
	            return value;
	        }

	        @Override
	        public boolean equals(Object obj) {

	            // Same reference
	            if (this == obj)
	                return true;

	            // Null check
	            if (obj == null)
	                return false;

	            // Type check
	            if (getClass() != obj.getClass())
	                return false;

	            // Cast
	            Feet other = (Feet) obj;

	            // Compare double safely
	            return Double.compare(this.value, other.value) == 0;
	        }

	        @Override
	        public int hashCode() {
	            return Double.hashCode(value);
	        }
	    }


	    // INCHES CLASS
	    public static class Inches {

	        private final double value;

	        public Inches(double value) {
	            this.value = value;
	        }

	        public double getValue() {
	            return value;
	        }

	        @Override
	        public boolean equals(Object obj) {

	            // Same reference
	            if (this == obj)
	                return true;

	            // Null check
	            if (obj == null)
	                return false;

	            // Type check
	            if (getClass() != obj.getClass())
	                return false;

	            // Cast
	            Inches other = (Inches) obj;

	            // Compare safely
	            return Double.compare(this.value, other.value) == 0;
	        }

	        @Override
	        public int hashCode() {
	            return Double.hashCode(value);
	        }
	    }


	    //STATIC METHODS

	    // Feet equality check
	    public static boolean compareFeet(double value1, double value2) {

	        Feet f1 = new Feet(value1);
	        Feet f2 = new Feet(value2);

	        return f1.equals(f2);
	    }


	    // Inches equality check
	    public static boolean compareInches(double value1, double value2) {

	        Inches i1 = new Inches(value1);
	        Inches i2 = new Inches(value2);

	        return i1.equals(i2);
	    }


	    // MAIN METHOD
	    public static void main(String[] args) {

	        // Feet comparison
	        boolean feetResult = compareFeet(1.0, 1.0);
	        System.out.println("Feet comparison result: " + feetResult);

	        // Inches comparison
	        boolean inchesResult = compareInches(1.0, 1.0);
	        System.out.println("Inches comparison result: " + inchesResult);
	    }
	}
