
package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void when5on5() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(5);
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 9, 12, 15},
                {4, 8, 12, 16, 20},
                {5, 10, 15, 20, 25},
        };
        assertThat(table, is(expect));
    }
    @Test
    public void when1on1() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(1);
        int[][] expect = {
                {1}
        };
        assertThat(table, is(expect));
    }
    @Test
    public void when0on0() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(0);
        int[][] expect = {};
        assertThat(table, is(expect));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void whenMinus5OnMinus5() {
        Matrix matrix = new Matrix();
        matrix.multiple(-5);

    }
}