package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbersTest {
    @Test
    public void whenNumberIsEvenThenTrue() {
        EvenNumbers evenNumbers = new EvenNumbers();
        boolean result = evenNumbers.isNumber(new ByteArrayInputStream("532".getBytes()));
        assertThat(result, is(true));
    }
    @Test
    public void whenNumberIsNotEvenThenFalse() {
        EvenNumbers evenNumbers = new EvenNumbers();
        boolean result = evenNumbers.isNumber(new ByteArrayInputStream("11".getBytes()));
        assertThat(result, is(false));
    }
    @Test (expected = NumberFormatException.class)
    public void whenNoNumberThenExpected() {
        EvenNumbers evenNumbers = new EvenNumbers();
        boolean result = evenNumbers.isNumber(new ByteArrayInputStream("1d".getBytes()));
    }
}
