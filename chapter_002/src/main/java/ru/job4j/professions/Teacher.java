package ru.job4j.professions;

public class Teacher extends Profession{

    public Teacher(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Teacher";
    }
    public boolean teach(Student student) {}
}
