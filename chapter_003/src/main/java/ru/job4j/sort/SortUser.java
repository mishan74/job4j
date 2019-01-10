package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {
    public Set<User> sort(List<User> users) {
        return new TreeSet(users);
    }

    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().toCharArray().length, o2.getName().toCharArray().length);
            }
        });
        return users;
    }

    public List<User> sortByAllFields(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int compareName = o1.getName().compareTo(o2.getName());
                return compareName != 0 ? compareName : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return users;
    }
}
