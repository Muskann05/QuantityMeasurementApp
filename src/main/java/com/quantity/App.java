package com.quantity;


public class App {

    public static void main(String[] args) {

        // Weight Equality
        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .equals(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );

        // Weight Conversion
        System.out.println(
                new QuantityWeight(2.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM)
        );

        // Weight Addition
        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );

        // Explicit target unit
        System.out.println(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM)
        );
    }
}