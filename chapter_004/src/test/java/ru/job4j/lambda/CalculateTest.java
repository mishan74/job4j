package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CalculateTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        Calculate calculate = new Calculate();
        List<Double> result = calculate.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        Calculate calculate = new Calculate();
        List<Double> result = calculate.diapason(4, 8, x -> 2 * Math.pow(x, 2) + x * 3);
        List<Double> expected = Arrays.asList(44D, 65D, 90D, 119D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmFunctionThenLogarithmResults() {
        Calculate calculate = new Calculate();
        List<Double> result = calculate.diapason(5, 9, Math::log);
        List<Double> expected = Arrays.asList(Math.log(5), Math.log(6), Math.log(7), Math.log(8));
        assertThat(result, is(expected));
    }
}
