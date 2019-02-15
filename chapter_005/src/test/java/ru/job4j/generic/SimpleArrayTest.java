package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    SimpleArray<String> sa;

    @Before
    public void setUp() {
        sa = new SimpleArray<>(4);
    }

    @Test
    public void whenAddElementsThenAdded() {
        sa.add("Hello");
        sa.add("Hello");
        sa.add("World");
        sa.add("World");
        assertThat(sa.get(0), is("Hello"));
        assertThat(sa.get(1), is("Hello"));
        assertThat(sa.get(2), is("World"));
        assertThat(sa.get(3), is("World"));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddMoreElementsThanCanThenException() {
        sa.add("Hello");
        sa.add("Hello");
        sa.add("Hello");
        sa.add("Hello");
        sa.add("Hello");
    }

    @Test
    public void whenSetElementThenSet() {
        sa.add("First value");
        String sets =  sa.set(0, "New value");
        assertThat(sets, is("New value"));
        assertThat(sa.get(0), is("New value"));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetOutOfBoundElementThenException() {
        sa.set(5, "Exception");
    }

    @Test
    public void whenRemoveThenRemoved() {
        sa.add("Hello");
        sa.add("My");
        sa.add("Beautiful");
        sa.add("World");
        String deleted = sa.remove(2);
        assertThat(deleted, is("Beautiful"));
        assertThat(sa.get(0), is("Hello"));
        assertThat(sa.get(1), is("My"));
        assertThat(sa.get(2), is("World"));
        assertNull(sa.get(3));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenRemoveOnOutOfBoundIndexThenException() {
        sa.remove(4);
    }
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenIter() {
        sa.add("Hello");
        sa.add("My");
        sa.add("Beautiful");
        sa.add("World");
        Iterator<String> iter = sa.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Hello"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("My"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Beautiful"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("World"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

}