package ru.job4j.tracker;

import com.sun.istack.internal.Nullable;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddFiveElementsDeleteOneThenFourElements() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "firstDescrition", 1313L);
        Item second = new Item("second", "secondDescrition", 1212L);
        Item third = new Item("third", "thirdDescrition", 333L);
        Item fours = new Item("fours", "foursDescrition", 4443L);
        Item fives = new Item("fives", "fivesDescrition", 553L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fours);
        tracker.add(fives);
        tracker.delete(third.getId());
        Item[] expect = new Item[]{first, second, fours , fives};
        assertThat(tracker.findAll(), is(expect));
    }

    @Test
    public void whenAddFiveElementsThreeSameNameThenFoundsThreeElements() {
        Tracker tracker = new Tracker();
        Item first = new Item("test", "firstDescrition", 1313L);
        Item second = new Item("second", "secondDescrition", 1212L);
        Item third = new Item("test", "thirdDescrition", 333L);
        Item fours = new Item("fours", "foursDescrition", 4443L);
        Item fives = new Item("test", "fivesDescrition", 553L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fours);
        tracker.add(fives);
        Item[] expect = new Item[]{first, third, fives};
        assertThat(tracker.findByName("test"), is(expect));
    }

    @Test (expected = NullPointerException.class)
    public void whenIncorrectIdThenNull() {
        Tracker tracker = new Tracker();
        Item first = new Item("test", "firstDescrition", 1313L);
        assertNull(tracker.findById("first"));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription",123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenAddNullItemThenTrackerIgnoreIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription",123L);
        tracker.add(item);
        tracker.add(null);
        assertThat(tracker.findAll(), is(new Item[]{item}));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1","testDescription",123L);
        tracker.add(previous);
        Item next = new Item("test2","testDescription2",1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

}
