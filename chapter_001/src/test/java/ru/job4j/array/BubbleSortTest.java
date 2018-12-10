package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] rst = bubbleSort.sort(array);
        int[] expect = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenSortArrayWithNegativeTenElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {1, 5, 4, -2, 3, 1, 7, -8, 0, 5};
        int[] rst = bubbleSort.sort(array);
        int[] expect = new int[] {-8, -2, 0, 1, 1, 3, 4, 5, 5, 7};
        assertThat(rst, is(expect));
    }
    @Test
    public void whenSortArrayWithOneElementsThenReturnArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {5};
        int[] rst = bubbleSort.sort(array);
        int[] expect = new int[] {5};
        assertThat(rst, is(expect));
    }
    @Test
    public void whenSortArrayWithNoElementsThenReturnEmptyArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = new int[] {};
        int[] rst = bubbleSort.sort(array);
        int[] expect = new int[] {};
        assertThat(rst, is(expect));
    }
}

