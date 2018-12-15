package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySortedTest {
    @Test
    public void whenSortArrayWithThreeElementsThenSortedArray() {
        ArraySorted arraySorted = new ArraySorted();
        int[] first = new int[] {1, 4, 5};
        int[] second = new int[] {1, 2, 3};
        int[] rst = arraySorted.sortedArray(first, second);
        int[] expect = new int[] {1, 1, 2, 3, 4, 5};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenFirstArraySmallerSecondThenSortedArray() {
        ArraySorted arraySorted = new ArraySorted();
        int[] first = new int[] {1, 4, 100};
        int[] second = new int[] {1, 2, 3, 7, 9};
        int[] rst = arraySorted.sortedArray(first, second);
        int[] expect = new int[] {1, 1, 2, 3, 4, 7, 9, 100};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenSecondArraySmallerFirstThenSortedArray() {
        ArraySorted arraySorted = new ArraySorted();
        int[] first = new int[] {1, 2, 3, 7, 9};
        int[] second = new int[] {1, 4, 100};
        int[] rst = arraySorted.sortedArray(first, second);
        int[] expect = new int[] {1, 1, 2, 3, 4, 7, 9, 100};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenSecondArrayEmptyThenFirstArray() {
        ArraySorted arraySorted = new ArraySorted();
        int[] first = new int[] {1, 2, 3, 7, 9};
        int[] second = new int[] {};
        int[] rst = arraySorted.sortedArray(first, second);
        int[] expect = new int[] {1, 2, 3, 7, 9};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenFirstArrayEmptyThenSecondArray() {
        ArraySorted arraySorted = new ArraySorted();
        int[] first = new int[] {};
        int[] second = new int[] {1, 4, 100};
        int[] rst = arraySorted.sortedArray(first, second);
        int[] expect = new int[] {1, 4, 100};
        assertThat(rst, is(expect));
    }
}
