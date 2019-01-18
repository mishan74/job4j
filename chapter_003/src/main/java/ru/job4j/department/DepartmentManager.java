package ru.job4j.department;

import java.util.*;

public class DepartmentManager {
    private final Set<Department> departments = new TreeSet<>();

    public void add(String name) {
        String[] names = name.split("\\\\");
        StringJoiner tmp = new StringJoiner("\\");
        for (String s : names) {
            tmp.add(s);
            departments.add(new Department(tmp.toString()));
        }
    }

    public void add(String[] name) {
        for (String s : name) {
            add(s);
        }
    }

    public Department[] getSort() {
        return departments.toArray(new Department[]{});
    }

    public Department[] getReverseSort() {
        Department[] result = departments.toArray(new Department[]{});
        Arrays.sort(result, new ReverseDepartmentComparator());
        return result;
    }
}
