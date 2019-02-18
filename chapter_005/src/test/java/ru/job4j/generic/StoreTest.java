package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreTest {
    Role r1;
    Role r2;
    User u1;
    User u2;
    UserStore us;
    RoleStore rs;

    @Before
    public void init() {
        us = new UserStore(3);
        rs = new RoleStore(3);
        r1 = new Role("54");
        r2 = new Role("95");
        u1 = new User("58");
        u2 = new User("99");
    }
    @Test
    public void whenAddThenAdded() {
        us.add(u1);
        us.add(u2);
        rs.add(r1);
        rs.add(r2);
        assertThat(us.findById(u1.getId()), is(u1));
        assertThat(us.findById(u2.getId()), is(u2));
        assertThat(rs.findById(r1.getId()), is(r1));
        assertThat(rs.findById(r2.getId()), is(r2));

    }
    @Test
    public void whenFindEmptyThenNull() {
        assertNull(us.findById(u1.getId()));
    }

    @Test
    public void whenReplaceThenTrue() {
        us.add(u1);
        assertThat(us.replace(u1.getId(), u2), is(true));
        assertThat(us.get(0), is(u2));
        assertThat(us.findById(u2.getId()), is(u2));
        assertNull(us.findById(u1.getId()));
    }

    @SuppressWarnings("ConstantConditions")
    @Test (expected = ClassCastException.class)
    public void canNotAddedAnotherClass() {
        Base b = new Role("15");
        us.add((User) b);
    }

    @Test
    public void whenDeletedThenTrue() {
        Role b = new Role("15");
        rs.add(b);
        rs.add(r1);
        rs.add(r2);
        assertTrue(rs.delete(r2.getId()));
        assertThat(rs.findById(r1.getId()), is(r1));
        assertNull(rs.findById(r2.getId()));
        assertThat(rs.findById(b.getId()), is(b));
    }
}