package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] array;
    private int index = 0;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    private IntStream stream() {
        return Stream
                .of(array)
                .flatMapToInt(IntStream::of)
                .skip(index);
    }
    @Override
    public boolean hasNext() {
        return stream()
                .anyMatch(x -> x % 2 == 0);
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result = stream()
                .filter(x -> x % 2 == 0)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Has no element more"));
        index +=  stream().takeWhile(x -> x % 2 == 1).count() + 1;
        return result;
    }
}