package ru.job4j.lambda;

import java.util.Arrays;

public class Stream {
    public int sort(int[] array) {
       return Arrays.stream(array)
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(0, (acc, x) -> acc + x);
    }
}
