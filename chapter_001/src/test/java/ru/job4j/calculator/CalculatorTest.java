package ru.job4j.calculator;

import org.junit.Test;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenThreeMultipleFourThenTwelve() {
        Calculator calc = new Calculator();
        calc.multiple(3D, 4D);
        double result = calc.getResult();
        double expected = 12D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenFiveSubtractThreeThenTwo() {
        Calculator calc = new Calculator();
        calc.subtract(5D, 3D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSixDivTwoThenThree() {
        Calculator calc = new Calculator();
        calc.div(6D, 2D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenTwoDivByZeroThenInfinity() {
        Calculator calc = new Calculator();
        calc.div(2D, 0D);
        double result = calc.getResult();
        assertThat(result, is(Infinity));
    }
}
