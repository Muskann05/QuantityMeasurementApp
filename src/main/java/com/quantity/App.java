package com.quantity;

public class App {

    public static void main(String[] args) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(q1.add(q2));

        Quantity<LengthUnit> q3 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q4 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println(q3.subtract(q4));

        Quantity<LengthUnit> q5 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q6 =
                new Quantity<>(2.0, LengthUnit.FEET);

        System.out.println(q5.divide(q6));
    }
}