package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SortUser {
    public Set<User> sort(List<User> users) {
        return new TreeSet(users);
    }

    public List<User> sortNameLength(List<User> users) {
        return users.stream().sorted(new CompareByName()).collect(Collectors.toList());
    }

    public List<User> sortByAllFields(List<User> users) {
        return users.stream().sorted().collect(Collectors.toList());
    }

    class CompareByName implements Comparator<User> {

        @Override
        public int compare(User o1, User o2) {
            return Integer.compare(o1.getName().length(), o2.getName().length());
        }
    }
}
