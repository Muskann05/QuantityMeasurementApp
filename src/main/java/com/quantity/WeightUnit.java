package com.quantity;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactorToKilogram;

    WeightUnit(double conversionFactorToKilogram) {
        this.conversionFactorToKilogram = conversionFactorToKilogram;
    }

    public double getConversionFactor() {
        return conversionFactorToKilogram;
    }

    // Convert to base unit (Kilogram)
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToKilogram;
    }

    // Convert from base unit (Kilogram)
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToKilogram;
    }
}