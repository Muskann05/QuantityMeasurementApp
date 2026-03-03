package com.quantity;


public class App {

    public static void main(String[] args) {

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET));

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES));

        System.out.println(
            QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS));
    }
}