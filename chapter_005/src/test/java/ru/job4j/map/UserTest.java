package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class UserTest {
    @Test
    public void testMethodForMap() {
        User user1 = new User("Viktor", 2, new GregorianCalendar(1888, Calendar.MARCH,12));
        User user2 = new User("Viktor", 2, new GregorianCalendar(1888, Calendar.MARCH,12));

        Map<String, Object> map = new HashMap();
        map.put("First", user1);
        map.put("Second", user2);

        System.out.println(map);


    }
}
