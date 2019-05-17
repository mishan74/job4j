package ru.job4j.sql;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.tracker.Item;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */

public class TrackerSQLTest {
    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenAddFiveElementsDeleteOneThenFourElements() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = new Item("first", "firstDescription");
            Item second = new Item("second", "secondDescription");
            tracker.add(first);
            tracker.add(second);
            List<Item> expect = new LinkedList<>();
            LinkedList<Item> res = (LinkedList) tracker.findAll();

            assertEquals(res.getFirst(), first);
            assertEquals(res.getLast(), second);
            assertTrue(tracker.delete(first.getId()));
            assertTrue(tracker.delete(second.getId()));
        }
    }

    @Test
    public void whenAddFiveElementsThreeSameNameThenFoundsThreeElements() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item first = new Item("test", "firstDescription");
            Item second = new Item("second", "secondDescription");
            Item third = new Item("test", "thirdDescription");
            Item fours = new Item("fours", "foursDescription");
            Item fives = new Item("test", "fivesDescription");
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            tracker.add(fours);
            tracker.add(fives);
            LinkedList<Item> res = (LinkedList<Item>) tracker.findByName("test");

            assertEquals(res.get(0), first);
            assertEquals(res.get(1), third);
            assertEquals(res.get(2), fives);
            assertTrue(tracker.delete(first.getId()));
            assertTrue(tracker.delete(second.getId()));
            assertTrue(tracker.delete(third.getId()));
            assertTrue(tracker.delete(fours.getId()));
            assertTrue(tracker.delete(fives.getId()));
        }
    }


    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item item = new Item("test1", "testDescription", 123L);
            tracker.add(item);
            assertEquals(tracker.findAll().get(0), item);
            assertTrue(tracker.delete(item.getId()));
        }
    }


    @Test
    public void whenAddItemThenSameItemReturn() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item item = new Item("test1", "testDescription", 123L);
            Item result = tracker.add(item);
            assertThat(result, Is.is(item));
            assertTrue(tracker.delete(item.getId()));
        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            tracker.init();
            Item previous = new Item("test1", "testDescription");
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2");
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), Is.is("test2"));
            assertTrue(tracker.delete(next.getId()));
        }
    }
}