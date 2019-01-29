package ru.job4j.department;

import java.util.*;

public class DepartmentManager {
    private final Set<Department> departments = new TreeSet<>();

    public void add(String name) {
        String[] names = name.split("\\\\");
        StringJoiner tmp = new StringJoiner("\\");
        //for (String s : names) {
        //    tmp.add(s);
        //    departments.add(new Department(tmp.toString()));
            Arrays.stream(names).forEach((t)-> {
                    tmp.add(t);
                    departments.add(new Department(tmp.toString()));
            });
        }
    //}

    public void add(String[] name) {
        //for (String s : name) {
        //    add(s);
        //}
        Arrays.stream(name).forEach(this::add);
    }

    public Department[] getSort() {
       // return departments.toArray(new Department[]{});
        return departments.toArray(new Department[0]);

    }

    public Department[] getReverseSort() {
        //Department[] result = departments.toArray(new Department[]{});
        // Arrays.sort(result, new ReverseDepartmentComparator());
        // return result;
        return departments.stream()
                .sorted(new ReverseDepartmentComparator())
                .toArray(Department[]::new);
    }
}
