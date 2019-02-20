package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueueTest {
    @Test
    public void whenAddedThreeElementsAndPollThenThatResult() {
        SimpleQueue<String> ss = new SimpleQueue<>();
        ss.push("Uno");
        ss.push("Dos");
        ss.push("Tres");
        assertThat(ss.poll(), is("Uno"));
        ss.push("Cuatro");
        assertThat(ss.poll(), is("Dos"));
        ss.push("One More");
        assertThat(ss.poll(), is("Tres"));
        assertThat(ss.poll(), is("Cuatro"));
        ss.push("One More");
        assertThat(ss.poll(), is("One More"));
    }
}
