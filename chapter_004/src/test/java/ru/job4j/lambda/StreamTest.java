package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StreamTest {
    @Test
    public void whenConsiderArrayThenResult() {
        Stream stream = new Stream();
        int expected = stream.sort(new int[]{5, 2, 4, 3, 1, 6, 4});
        assertThat(expected, is(72));
    }

    @Test
    public void whenUnevenArrayThenZero() {
        Stream stream = new Stream();
        int expected = stream.sort(new int[]{5, 3, 1});
        assertThat(expected, is(0));
    }

}