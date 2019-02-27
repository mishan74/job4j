package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class UserTest {
    @Test
    public void testMethodForMap() {
        User user1 = new User("Viktor", 2, new GregorianCalendar(1888, Calendar.MARCH, 12));
        User user2 = new User("Viktor", 2, new GregorianCalendar(1888, Calendar.MARCH, 12));

        Map<User, Object> map = new HashMap();
        assertNull(map.put(user1, "First"));
        assertThat(map.put(user2, "Second"), is("First"));


    }
}
