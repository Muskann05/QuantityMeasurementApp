package com.spring.unit;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    // Convert → Base (KILOGRAM)
    @Override
    public double toBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert ← Base
    @Override
    public double fromBaseUnit(double value) {
        return value / conversionFactor;
    }

    @Override
    public void validateOperationSupport(String operation) {
    }
}