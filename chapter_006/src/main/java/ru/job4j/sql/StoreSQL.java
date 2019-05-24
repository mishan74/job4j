package ru.job4j.sql;

import ru.job4j.socket.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config.load();
    }

    public void init() {
        try {
            this.connect = DriverManager.getConnection(config.value("urlsql"));
            this.connect.setAutoCommit(false);
            System.out.println("Выполнено подключение к БД " + config.value("urlsql"));
            String create = "CREATE TABLE IF NOT EXISTS entry (field INTEGER);";
            String drop = "DROP TABLE IF EXISTS entry";
            try (Statement statement = connect.createStatement()) {
                statement.executeUpdate(drop);
                statement.executeUpdate(create);
                this.connect.commit();
                System.out.println("Таблица создана");
            } catch (Exception e) {
                this.connect.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        try (PreparedStatement ps = connect.prepareStatement("INSERT INTO entry VALUES (?);")) {
            for (int i = 1; i <= size; i++) {
                ps.setInt(1, i);
                ps.addBatch();
            }
            ps.executeBatch();
            this.connect.commit();
            System.out.println("Таблица сгенерирована");
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Entry> load() {
       List<Entry> entries = new ArrayList<>();
        try {
            PreparedStatement ps = connect.prepareStatement("SELECT field FROM entry;");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                entries.add(new Entry(result.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
