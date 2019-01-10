package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenAdd3UsersThenSort() {
        List<User> users = new ArrayList<>();
        users.add(new User("Viktor", 37));
        users.add(new User("Pavel", 28));
        users.add(new User("Svyatoslav", 352));
        users.add(new User("Pavel", 27));
        String result = new SortUser().sort(users).iterator().next().getName();
        assertThat(result, is("Pavel"));
    }
    @Test
    public void whenAddUsersSimpleNameThenSort() {
        List<User> users = new ArrayList<>();
        users.add(new User("Viktor", 37));
        users.add(new User("Pavel", 28));
        users.add(new User("Pavel", 17));
        users.add(new User("Pavel", 27));
        int result = new SortUser().sort(users).iterator().next().getAge();
        assertThat(result, is(17));
    }

    @Test
    public void whenAddUsersAnotherNameLengthThenSort() {
        List<User> users = new ArrayList<>();
        users.add(new User("Viktor", 37));
        users.add(new User("Pavel", 28));
        users.add(new User("Yan", 17));
        users.add(new User("Pavel", 27));
        String result = new SortUser().sortNameLength(users).iterator().next().getName();
        assertThat(result, is("Yan"));
    }

    @Test
    public void whenUsersSortAllFieldsThenSort() {
        List<User> users = new ArrayList<>();
        users.add(new User("Viktor", 37));
        users.add(new User("Anna", 28));
        users.add(new User("Yan", 17));
        users.add(new User("Pavel", 27));
        String result = new SortUser().sortByAllFields(users).iterator().next().getName();
        assertThat(result, is("Anna"));
    }
    @Test
    public void whenUsersSortAllFieldsWithSimpleNameThenSort() {
        List<User> users = new ArrayList<>();
        users.add(new User("Viktor", 37));
        users.add(new User("Boris", 28));
        users.add(new User("Boris", 17));
        users.add(new User("Pavel", 27));
        int result = new SortUser().sortByAllFields(users).iterator().next().getAge();
        assertThat(result, is(17));
    }
}
