package com.kodilla.stream.array;

import java.util.stream.IntStream;

public class ArrayOperations {
    public static double getAverage(int[] numbers){
         IntStream showElements = IntStream.range(0, numbers.length)
                .map(n -> numbers[n]);
        System.out.println(showElements);

        double getAverage = IntStream.range(0,numbers.length)
                .map(n -> numbers[n])
                .average().getAsDouble();
        return getAverage;
    }
}
