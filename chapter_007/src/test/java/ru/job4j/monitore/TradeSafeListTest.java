package ru.job4j.monitore;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class TradeSafeListTest {
    final TradeSafeList<String> tradeSafeList = new TradeSafeList<>(new DynamicList<>());
    @Before
    public void init() {
        tradeSafeList.add("First");
        tradeSafeList.add("Second");

    }


    @Test
    public void whenIteratorThenIterate() throws InterruptedException {
        Iterator<String> iter = tradeSafeList.iterator();

        new Thread(() -> tradeSafeList.add("Third")).start();

        Thread.sleep(1000);

        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("First"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Second"));
        assertThat(iter.hasNext(), is(false));

        Iterator<String> iter2 = tradeSafeList.iterator();

        assertThat(iter2.hasNext(), is(true));
        assertThat(iter2.next(), is("First"));
        assertThat(iter2.hasNext(), is(true));
        assertThat(iter2.next(), is("Second"));
        assertThat(iter2.hasNext(), is(true));
        assertThat(iter2.next(), is("Third"));
        assertThat(iter2.hasNext(), is(false));

    }
}
