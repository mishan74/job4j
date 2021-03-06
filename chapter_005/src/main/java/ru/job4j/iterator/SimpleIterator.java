package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Rozdin.
 * @version $id$
 * @since 0.1
 *
 * Class SimpleIterator for matrix arrays.
 */
public class SimpleIterator implements Iterator<Integer> {
    private final int[][] values;
    private int indexOut = 0;
    private int indexIn = 0;

    public SimpleIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return indexOut < values.length - 1 || indexIn < values[indexOut].length;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result;
         if (indexIn == values[indexOut].length) {
                result = values[++indexOut][0];
                indexIn = 1;
         } else {
            result = values[indexOut][indexIn++];
        }
         return result;
    }
}
