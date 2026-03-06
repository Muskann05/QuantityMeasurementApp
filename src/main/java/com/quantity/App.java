package com.quantity;

public class App {

    public static void main(String[] args) {

        Quantity<LengthUnit> length =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inch =
                new Quantity<>(12, LengthUnit.INCH);

        System.out.println("Length Equality: " + length.equals(inch));

        Quantity<WeightUnit> kg =
                new Quantity<>(1, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + kg.equals(gram));

        Quantity<TemperatureUnit> temp =
                new Quantity<>(0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> fahr =
                new Quantity<>(32, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temperature Equality: " + temp.equals(fahr));
    }
}