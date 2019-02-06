package ru.job4j.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable<Student> {
    private int bound;
    private String name;

    public Student(String name, int bound) {
        this.bound = bound;
        this.name = name;
    }

    public int getBound() {
        return bound;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.bound, o.getBound());
    }
    static List<Student> levelOf(List<Student> students, int bound) {
        return students
                .stream()
                .flatMap(Stream::ofNullable)
                .sorted()
                .takeWhile(k -> k.getBound() < bound)
                .collect(Collectors.toList());
    }
}
