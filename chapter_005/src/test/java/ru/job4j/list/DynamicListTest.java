package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class DynamicListTest {
    final DynamicList<String> dynamicList = new DynamicList<>();
    @Before
    public void init() {
        dynamicList.add("First");
        dynamicList.add("Second");
        dynamicList.add("Third");
        dynamicList.add("Fourth");
        dynamicList.add("Fifth");
        dynamicList.add("Sixth");
        dynamicList.add("Seventh");
        dynamicList.add("Eighth");
        dynamicList.add("Ninth");
        dynamicList.add("Tenth");
    }

    @Test
    public void whenAddedNewElementThenCapacityIncrease() {
        dynamicList.add("Eleventh");
        assertThat(dynamicList.get(10), is("Eleventh"));
    }
    @Test
    public void whenGetFifthIndexThenSix() {
        assertThat(dynamicList.get(5), is("Sixth"));
    }

    @Test
    public void whenGetFifthIndexAndAddByIndexThenSix() {
        dynamicList.add("Hello", 2);
        assertThat(dynamicList.get(6), is("Sixth"));
        assertThat(dynamicList.get(2), is("Hello"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenGetNegativeThenException() {
        dynamicList.get(-5);
    }


    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenIterate() {
        Iterator<String> iter = dynamicList.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("First"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Second"));
        assertThat(iter.next(), is("Third"));
        assertThat(iter.next(), is("Fourth"));
        assertThat(iter.next(), is("Fifth"));
        assertThat(iter.next(), is("Sixth"));
        assertThat(iter.next(), is("Seventh"));
        assertThat(iter.next(), is("Eighth"));
        assertThat(iter.next(), is("Ninth"));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is("Tenth"));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyCollectionThenIteratorNextException() {
        Iterator<String> iter = dynamicList.iterator();
        assertThat(iter.hasNext(), is(true));
        dynamicList.add("Eleventh");
        iter.next();
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyCollectionThenIteratorHasNextException() {
        Iterator<String> iter = dynamicList.iterator();
        assertThat(iter.hasNext(), is(true));
        dynamicList.add("Eleventh");
        iter.hasNext();
    }
}
