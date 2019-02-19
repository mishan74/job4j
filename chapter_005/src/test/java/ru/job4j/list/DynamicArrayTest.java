package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class DynamicArrayTest {
    DynamicArray<String> dynamicArray = new DynamicArray<>();
    @Before
    public void init() {
    dynamicArray.add("First");
    dynamicArray.add("Second");
    dynamicArray.add("Third");
    dynamicArray.add("Fourth");
    dynamicArray.add("Fifth");
    dynamicArray.add("Sixth");
    dynamicArray.add("Seventh");
    dynamicArray.add("Eighth");
    dynamicArray.add("Ninth");
    dynamicArray.add("Tenth");
    }

    @Test
    public void whenAddedNewElementThenCapacityIncrease() {
        dynamicArray.add("Eleventh");
        assertThat(dynamicArray.get(10), is("Eleventh"));
    }
    @Test
    public void whenGetFifthIndexThenSix() {
        assertThat(dynamicArray.get(5), is("Sixth"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenGetNegativeThenException() {
        dynamicArray.get(-5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenInitialCapacityIsNegativeThenException() {
        dynamicArray = new DynamicArray<>(-3);
    }

    @Test
    public void whenIteratorThenIterate() {
        Iterator<String> iter = dynamicArray.iterator();
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
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyCollectionThenIteratorNextException() {
        Iterator<String> iter = dynamicArray.iterator();
        assertThat(iter.hasNext(), is(true));
        dynamicArray.add("Eleventh");
        iter.next();
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test (expected = ConcurrentModificationException.class)
    public void whenModifyCollectionThenIteratorHasNextException() {
        Iterator<String> iter = dynamicArray.iterator();
        assertThat(iter.hasNext(), is(true));
        dynamicArray.add("Eleventh");
        iter.hasNext();
    }
}
