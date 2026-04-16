package com.spring.measurement.unit;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MIILILITRE(0.001),
    GALLON(3.78541);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
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

    // Convert → Base (LITER)
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