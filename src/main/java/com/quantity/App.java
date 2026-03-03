package com.quantity;


public class App {

    public static void main(String[] args) {

        System.out.println(new QuantityLength(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES));

        System.out.println(new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES), LengthUnit.FEET));

        System.out.println(new QuantityLength(36.0, LengthUnit.INCHES)
                .equals(new QuantityLength(1.0, LengthUnit.YARDS)));

        System.out.println(new QuantityLength(1.0, LengthUnit.YARDS)
                .add(new QuantityLength(3.0, LengthUnit.FEET), LengthUnit.YARDS));

        System.out.println(new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .convertTo(LengthUnit.INCHES));
    }
}