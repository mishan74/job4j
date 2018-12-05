package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(5, 3);
        assertThat(result, is(5));
    }

    @Test
    public void whenSecondIsFirst() {
        Max maxim = new Max();
        int result = maxim.max(0, 0);
        assertThat(result, is(0));
    }

    @Test
    public void whenFirstMoreSecondAndThird() {
        Max maxim = new Max();
        int result = maxim.max(5, 2, 3);
        assertThat(result, is(5));
    }

    @Test
    public void whenThirdMoreSecondAndFirst() {
        Max maxim = new Max();
        int result = maxim.max(1, 2, 7);
        assertThat(result, is(7));
    }

    @Test
    public void whenThirdIsSecondAndFirst() {
        Max maxim = new Max();
        int result = maxim.max(8, 8, 8);
        assertThat(result, is(8));
    }

    @Test
    public void whenSecondMoreThirdAndFirst() {
        Max maxim = new Max();
        int result = maxim.max(0, 8, -5);
        assertThat(result, is(8));
    }
}
