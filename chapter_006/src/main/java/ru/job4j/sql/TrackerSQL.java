package ru.job4j.sql;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQL implements ITracker, Closeable {

    private Connection connection;

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into item (item_name, item_desc, item_comment, item_time)"
                        + "values (?, ?, ?, now());", Statement.RETURN_GENERATED_KEYS)) {
            String comments = "";
            if (item.getComments() != null) {
                for (String comment : item.getComments()) {
                    comments.concat(comment.concat(System.lineSeparator()));
                }
            }

            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setString(3, comments);
            ps.executeUpdate();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next()) {
                item.setId(Integer.toString(result.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
       boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                "update item set "
                        + "item_name = ?, item_desc = ?, item_comment = ?, item_time = ? "
                        + "where id = ?;"
        )) {
            StringBuilder comments = new StringBuilder();
            if (item.getComments() != null) {
                for (String comment : item.getComments()) {
                    comments.append(comment).append(System.lineSeparator());
                }
            }
            ps.setString(1, item.getName());
            ps.setString(2, item.getDesc());
            ps.setString(3, comments.toString());
            ps.setTimestamp(4, new Timestamp(item.getCreate()));
            ps.setInt(5, Integer.valueOf(id));
            result = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                "delete from item where id = ?;"
        )) {
            ps.setInt(1, Integer.valueOf(id));
            result = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        LinkedList<Item> items = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select * from item;"
        )) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                items.add(new Item(
                   result.getString("item_name"),
                   result.getString("item_desc"),
                   result.getTimestamp("item_time").getTime()
                ));
                items.getLast().setId(String.valueOf(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        LinkedList<Item> items = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "select * from item where item_name = ?;"
        )) {
            ps.setString(1, key);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                items.add(new Item(
                        result.getString("item_name"),
                        result.getString("item_desc"),
                        result.getTimestamp("item_time").getTime()
                ));
                items.getLast().setId(String.valueOf(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

        @Override
    public Item findById(String id) {
        Item item = null;
            try (PreparedStatement ps = connection.prepareStatement(
                    "select * from item where id = ?;"
            )) {
                ps.setInt(1, Integer.valueOf(id));
                ResultSet result = ps.executeQuery();
                if (result.next()) {
                    item = new Item(
                            result.getString("item_name"),
                            result.getString("item_desc"),
                            result.getTimestamp("item_time").getTime()
                    );
                    item.setId(String.valueOf(result.getInt("id")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return item;
    }



    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            this.setTableItems();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void setTableItems() {
        try (PreparedStatement ps =
                     this.connection.prepareStatement(
                             "create table if not exists item ("
                                     + "id serial primary key,"
                                     + "item_name varchar (50),"
                                     + "item_desc varchar (300),"
                                     + "item_comment varchar(300),"
                                     + "item_time timestamp );"
                     )
        ) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
