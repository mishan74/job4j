package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {

    @Test
    public void whenCalcFiveThenOneHundredTwenty() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        int expect = 120;
        assertThat(result, is(expect));
    }

    @Test
    public void whenCalcZeroThenOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        int expect = 1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenCalcNegativeThenZero() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(-5);
        int expect = 0;
        assertThat(result, is(expect));
    }
}
