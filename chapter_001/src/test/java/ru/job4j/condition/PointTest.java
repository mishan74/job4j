package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;


public class PointTest {

    @Test
    public void whenPointEqualsThenZero() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 0);
        double result = first.distanceTo(second);
        double expected = 0D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenFirstPoint5and2SecondPoint2and5Then4p24() {
        Point first = new Point(5, 2);
        Point second = new Point(2, 5);
        double result = first.distanceTo(second);
        double expected = 4.24D;
        assertThat(result, closeTo(expected, 0.1));
    }
    @Test
    public void whenFirstPointMinus5and2SecondPointMinus2and5Then4p24() {
        Point first = new Point(-5, 2);
        Point second = new Point(-2, 5);
        double result = first.distanceTo(second);
        double expected = 4.24D;
        assertThat(result, closeTo(expected, 0.1));
    }

    @Test
    public void firstPointDistanceEqualsSecondPointDistance() {
        Point first = new Point(-5, 2);
        Point second = new Point(-2, 5);
        double distance1to2 = first.distanceTo(second);
        double distance2to1 = second.distanceTo(first);
        boolean result = distance1to2 == distance2to1;
        assertThat(result, is(true));
    }

    @Test
    public void whenPointDistanceReflexThenZero() {
        Point point = new Point(-5, 2);
        double result = point.distanceTo(point);
        double expected = 0;
        assertThat(result, is(expected));
    }

}
