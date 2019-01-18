package ru.job4j.department;

import java.util.Objects;

public class Department implements Comparable<Department> {
    private final String name;

    public String getName() {
        return name;
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Department o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(name, that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
