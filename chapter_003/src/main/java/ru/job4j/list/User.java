package ru.job4j.list;

public class User {
    private final int id;
    private final String name;
    private final String city;

    public User(String name, String city) {
        this.id = (int) (Math.random() * 1000);
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return this.city;
    }
}
