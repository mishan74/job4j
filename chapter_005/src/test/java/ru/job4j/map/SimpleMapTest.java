package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleMapTest {
    SimpleMap<String, User> simpleMap;
    User user1;
    User user2;
    User user3;
    User user4;

    @Before
    public void init() {
        simpleMap = new SimpleMap<>();
        user1 = new User("Viktor", 2, new GregorianCalendar(1888, Calendar.MARCH, 12));
        user2 = new User("Petr", 1, new GregorianCalendar(1899, Calendar.APRIL, 1));
        user3 = new User("Vasily", 3, new GregorianCalendar(1999, Calendar.JULY, 5));
        user4 = new User("Evgeny", 0, new GregorianCalendar(1000, Calendar.JANUARY, 27));
    }

    @Test
    public void whenInsertThenAdded() {
    assertThat(simpleMap.insert(null, user1), is(true));
    assertThat(simpleMap.insert("b", user2), is(false));
    assertThat(simpleMap.insert("a", user3), is(true));
    assertThat(simpleMap.getTableLength(), is(6));
    assertThat(simpleMap.insert("null", user3), is(true));
    assertThat(simpleMap.insert("d", user3), is(true));
    assertThat(simpleMap.getTableLength(), is(12));
    }
    @Test
    public void whenDeleteThenDeleted() {
        simpleMap.insert("a", user1);
        assertThat(simpleMap.get("a"), is(user1));
        assertThat(simpleMap.delete("a"), is(true));
        assertNull(simpleMap.get("a"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenIter() {
        simpleMap.insert("a", user1);
        Iterator<IterNode<String, User>> iter = simpleMap.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(user1));
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }
    @Test (expected = ConcurrentModificationException.class)
    public void whenMapBeModifyThenIteratorException() {
        simpleMap.insert("a", user1);
        simpleMap.insert("b", user2);
        simpleMap.insert("c", user3);
        Iterator<IterNode<String, User>> iter = simpleMap.iterator();
        assertThat(iter.hasNext(), is(true));
        simpleMap.insert("d", user4);
        iter.next();
    }
    @Test
    public void simpleIteratorTest() {
        simpleMap.insert("a", user1);
        simpleMap.insert("b", user2);
        simpleMap.insert("d", user3);
        Iterator<IterNode<String, User>> iter = simpleMap.iterator();
        assertThat(iter.hasNext(), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(true));
        iter.next();
        assertThat(iter.hasNext(), is(false));
    }
}
