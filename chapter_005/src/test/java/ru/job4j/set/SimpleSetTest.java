package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {
    @Test
    public void whenAddInSetThenIteratorContains() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("First");
        assertThat(ss.iterator().next(), is("First"));
    }
    @Test
    public void whenAddAnotherInSetThenIteratorDoNotHasNext() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("First");
        ss.add("First");
        Iterator iterator = ss.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("First"));
        assertThat(iterator.hasNext(), is(false));
    }
    @Test
    public void whenAddInSetThenContains() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("First");
        ss.contains("First");
    }
}
