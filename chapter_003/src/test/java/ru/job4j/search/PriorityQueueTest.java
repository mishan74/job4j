package ru.job4j.search;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
    @Test
    public void whenEqualsPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("one", 1));
        queue.put(new Task("oneMore", 1));
        Task result = queue.take();
        assertThat(result.getDesc(), is("one"));
    }
    @Test
    public void whenNoEqualsPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("one", 1));
        queue.put(new Task("two", 5));
        Task result = queue.take();
        assertThat(result.getDesc(), is("one"));
    }
}