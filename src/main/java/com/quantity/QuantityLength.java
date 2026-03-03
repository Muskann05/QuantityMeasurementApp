package com.quantity;

public final class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 0.001;

    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to base unit (feet)
    private double toBaseUnit() {
        return unit.toFeet(value);
    }

    // UC6
    // Addition returning result in first operand unit
    public QuantityLength add(QuantityLength other) {
        return add(this, other, this.unit);
    }

    // UC7 
    // Explicit target unit
    public static QuantityLength add(
            QuantityLength l1,
            QuantityLength l2,
            LengthUnit targetUnit) {

        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Lengths cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!Double.isFinite(l1.value) || !Double.isFinite(l2.value))
            throw new IllegalArgumentException("Invalid numeric value");

        double sumInFeet = l1.toBaseUnit() + l2.toBaseUnit();

        double converted = targetUnit.fromFeet(sumInFeet);

        return new QuantityLength(converted, targetUnit);
    }

    // equals() with epsilon
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}