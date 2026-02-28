package com.quantity;


/*LENGTH UNIT ENUM */

enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(0.0328084);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}

/*VALUE OBJECT*/

class Quantity {

    private final double value;
    private final LengthUnit unit;

    public Quantity(double value, LengthUnit unit) {

        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid Input");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /*UC5 CONVERSION*/

    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target) {

        if (source == null || target == null ||
                !Double.isFinite(value))
            throw new IllegalArgumentException();

        double base = value * source.getFactor();
        return base / target.getFactor();
    }

    public Quantity convertTo(LengthUnit target) {
        return new Quantity(
                convert(value, unit, target),
                target);
    }

    /* UC6 ADDITION*/

    public Quantity add(Quantity other) {

        if (other == null)
            throw new IllegalArgumentException();

        double v1 = value * unit.getFactor();
        double v2 = other.value * other.unit.getFactor();

        double sumBase = v1 + v2;

        double result =
                sumBase / unit.getFactor();

        return new Quantity(result, unit);
    }

    /*EQUALITY*/

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Quantity)) return false;

        Quantity q = (Quantity) obj;

        double v1 = value * unit.getFactor();
        double v2 = q.value * q.unit.getFactor();

        return Math.abs(v1 - v2) < 0.0001;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

/*MAIN APPLICATION*/

public class App {

    public static void main(String[] args) {

        System.out.println("UC5 Conversion");

        System.out.println(
                Quantity.convert(1, LengthUnit.FEET,
                        LengthUnit.INCHES));

        System.out.println(
                Quantity.convert(3, LengthUnit.YARDS,
                        LengthUnit.FEET));

        System.out.println("\nUC6 Addition");

        Quantity q1 =
                new Quantity(1, LengthUnit.FEET);

        Quantity q2 =
                new Quantity(12, LengthUnit.INCHES);

        Quantity result = q1.add(q2);

        System.out.println(result);
    }
}