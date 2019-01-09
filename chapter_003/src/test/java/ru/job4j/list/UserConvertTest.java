package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenAddListThenMap() {
        List<User> users = new ArrayList<>();
        UserConvert userConvert = new UserConvert();
        users.add(new User("Victor", "Volgograg"));
        Map<Integer, User> map = userConvert.process(users);

        int expect = users.iterator().next().getId();
        int result = map.entrySet().iterator().next().getKey();

        assertThat(expect, is(result));
    }

    @Test
    public void whenAddListFewElementsThenMap() {
        List<User> users = new ArrayList<>();
        UserConvert userConvert = new UserConvert();
        users.add(new User("Victor", "Volgograg"));
        users.add(new User("Vasilisa", "Oz"));
        users.add(new User("Mikhail", "Sochi"));
        Map<Integer, User> map = userConvert.process(users);

        User expect = users.iterator().next();
        User result = map.get(expect.getId());

        assertThat(expect, is(result));
    }
}
