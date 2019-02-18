package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class BaseTest {
    @Test
    public void whenAddUserThenGetIdResult() {
        User user = new User("1");
        Role role = new Role("2");
        Base user1 = new User("3");
        Base role1 = new Role("4");
        Base roleNull = new Role(null);

        assertThat(user.getId(), is("1"));
        assertThat(role.getId(), is("2"));
        assertThat(user1.getId(), is("3"));
        assertThat(role1.getId(), is("4"));
        assertNull(roleNull.getId());
    }
}
