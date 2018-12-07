package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
      Counter counter = new Counter();
      int result = counter.add(1, 10);
      int expect = 30;
      assertThat(result, is(expect));
    }

    @Test
    public void whenStartMoreFinishThenZero() {
        Counter counter = new Counter();
        int result = counter.add(10, 1);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenStartIsFinishIsUnevenThenZero() {
        Counter counter = new Counter();
        int result = counter.add(9, 9);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenStartIsFinishIsEvenThenItNumber() {
        Counter counter = new Counter();
        int result = counter.add(10, 10);
        int expect = 10;
        assertThat(result, is(expect));
    }
}