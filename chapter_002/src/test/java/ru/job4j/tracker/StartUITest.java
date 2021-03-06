package ru.job4j.tracker;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;


public class StartUITest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker, output).init();
        tracker.findById(item.getId()).getName();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }
    @Test
    public void whenDeleteThenTrackerHasDeleteItem() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("first", "desc"));
        Item item2 = tracker.add(new Item("second", "desc"));
        Input input = new StubInput(new String[]{"3", item1.getId(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0), is(item2));
        assertNull(tracker.findById(item1.getId()));
    }

    @Test
    public void whenIncorrectDeleteIdThenNothingToDelete() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("first", "desc"));
        Item item2 = tracker.add(new Item("second", "desc"));
        Input input = new StubInput(new String[]{"3", "Incorrect_Id", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(tracker.findAll().get(0), is(item1));
        assertThat(tracker.findAll().get(1), is(item2));
    }
}
