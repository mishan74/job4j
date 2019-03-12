package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class UniqueTest {
    Unique unique;
    @Before
    public void init() {
        unique = new Unique();
    }

    @Test
    public void whenUniqueThenTrue() {
        String first = "Hello";
        String second = "He";
        assertThat(unique.isUnique(first, second), is(true));
    }
    @Test
    public void whenNoDuplicateThenTrue() {
        String first = "Hello";
        String second = "llHeo";
        assertThat(unique.isUnique(first, second), is(true));
    }
    @Test
    public void whenDuplicateThenFalse() {
        String first = "Hello";
        String second = "eHolll";
        assertThat(unique.isUnique(first, second), is(false));
    }

    @Test
    public void whenNotUniqueThenFalse() {
        String first = "Hello";
        String second = "he";
        assertThat(unique.isUnique(first, second), is(false));
    }
}
