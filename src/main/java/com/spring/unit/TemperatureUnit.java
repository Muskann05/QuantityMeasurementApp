package com.spring.unit;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS {
        public double toBaseUnit(double value) {
            return value;
        }

        public double fromBaseUnit(double value) {
            return value;
        }
    },

    FAHRENHEIT {
        public double toBaseUnit(double value) {
            return (value - 32) * 5 / 9;
        }

        public double fromBaseUnit(double value) {
            return (value * 9 / 5) + 32;
        }
    },

    KELVIN {
        public double toBaseUnit(double value) {
            return value - 273.15;
        }

        public double fromBaseUnit(double value) {
            return value + 273.15;
        }
    };


    @Override
    public String getUnitName() {
        return name(); // CELSIUS, FAHRENHEIT, KELVIN
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // Not used for temperature
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation + " operation"
        );
    }

    // Abstract methods
    public abstract double toBaseUnit(double value);
    public abstract double fromBaseUnit(double value);
}