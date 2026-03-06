package com.quantity;

public class App {

    public static void main(String[] args) {

        // LENGTH SUBTRACTION
        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result1 = q1.subtract(q2);

        System.out.println("Subtraction Result: " + result1);

        // EXPLICIT TARGET UNIT
        Quantity<LengthUnit> result2 =
                q1.subtract(q2, LengthUnit.INCHES);

        System.out.println("Explicit Target Unit: " + result2);

        // VOLUME SUBTRACTION
        Quantity<VolumeUnit> v1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> v2 =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Subtraction: " + v1.subtract(v2));

        // DIVISION
        Quantity<LengthUnit> d1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> d2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        double ratio = d1.divide(d2);

        System.out.println("Division Result: " + ratio);
    }
}