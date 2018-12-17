package ru.job4j.professions;

public class Engineer extends Profession {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Engineer";
    }
    public boolean build(House house) {

    }
}
