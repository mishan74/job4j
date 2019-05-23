package ru.job4j.sql;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */

public class TrackerSQLTest {
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddFiveElementsDeleteOneThenFourElements() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {

            Item first = new Item("first", "firstDescription");
            Item second = new Item("second", "secondDescription");
            tracker.add(first);
            tracker.add(second);
            List<Item> expect = new LinkedList<>();
            LinkedList<Item> res = (LinkedList) tracker.findAll();

            assertEquals(res.getFirst(), first);
            assertEquals(res.getLast(), second);

        }
    }

    @Test
    public void whenAddFiveElementsThreeSameNameThenFoundsThreeElements() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {

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
        }
    }


    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {

            Item item = new Item("test1", "testDescription", 123L);
            tracker.add(item);
            assertEquals(tracker.findAll().get(0), item);

        }
    }


    @Test
    public void whenAddItemThenSameItemReturn() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item("test1", "testDescription", 123L);
            Item result = tracker.add(item);
            assertThat(result, Is.is(item));

        }
    }

    @Test
    public void whenReplaceNameThenReturnNewName() throws SQLException {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {

            Item previous = new Item("test1", "testDescription");
            tracker.add(previous);
            Item next = new Item("test2", "testDescription2");
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            assertThat(tracker.findById(previous.getId()).getName(), Is.is("test2"));

        }
    }
}