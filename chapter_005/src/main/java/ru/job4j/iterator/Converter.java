package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<>() {
            Iterator<Integer> integerIterator = it.next();

            private void dropWhileEmpty() {
                while (it.hasNext() && !integerIterator.hasNext()) {
                    integerIterator = it.next();
                }
            }

            @Override
            public boolean hasNext() {
                dropWhileEmpty();
                return it.hasNext() || integerIterator.hasNext();
            }

            @Override
            public Integer next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return integerIterator.next();
            }
        };
    }
}