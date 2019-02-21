package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleStackTest {
    @Test
    public void whenAddedThreeElementsAndPollThenThatResult() {
        SimpleStack<String> ss = new SimpleStack<>(new SimpleArrayList());
        ss.push("Uno");
        ss.push("Dos");
        ss.push("Tres");
        assertThat(ss.poll(), is("Tres"));
        assertThat(ss.poll(), is("Dos"));
        assertThat(ss.poll(), is("Uno"));
    }
}
