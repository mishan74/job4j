package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddFiveElementsDeleteOneThenFourElements() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "firstDescription", 1313L);
        Item second = new Item("second", "secondDescription", 1212L);
        Item third = new Item("third", "thirdDescription", 333L);
        Item fours = new Item("fours", "foursDescription", 4443L);
        Item fives = new Item("fives", "fivesDescription", 553L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fours);
        tracker.add(fives);
        tracker.delete(third.getId());
        ArrayList<Item> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(first, second, fours, fives));
        assertThat(tracker.findAll(), is(expect));
    }

    @Test
    public void whenAddFiveElementsThreeSameNameThenFoundsThreeElements() {
        Tracker tracker = new Tracker();
        Item first = new Item("test", "firstDescription", 1313L);
        Item second = new Item("second", "secondDescription", 1212L);
        Item third = new Item("test", "thirdDescription", 333L);
        Item fours = new Item("fours", "foursDescription", 4443L);
        Item fives = new Item("test", "fivesDescription", 553L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fours);
        tracker.add(fives);
        ArrayList<Item> expect = new ArrayList<>();
        expect.addAll(Arrays.asList(first, third, fives));
        assertThat(tracker.findByName("test"), is(expect));
    }

    @Test
    public void whenIncorrectIdThenNull() {
        Tracker tracker = new Tracker();
        assertNull(tracker.findById("first"));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenAddNullItemThenTrackerIgnoreIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        tracker.add(null);
        assertThat(tracker.findAll(), is(Arrays.asList(item)));
    }

    @Test
    public void whenAddItemThenSameItemReturn() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item result = tracker.add(item);
        assertThat(result, is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}
