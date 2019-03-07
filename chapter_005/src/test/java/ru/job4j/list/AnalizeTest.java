package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class AnalizeTest {
    List<Analize.User> previous;
    Analize.User user1;
    Analize.User user2;
    Analize.User user3;
    Analize.User user4;
    Analize.User user5;
    @Before
    public void init() {
        user1 = new Analize.User(15, "Ivan");
        user2 = new Analize.User(12, "Evgeny");
        user3 = new Analize.User(16, "Tatiana");
        user4 = new Analize.User(88, "Mikhail");
        user5 = new Analize.User(19, "Sergei");
        previous = Arrays.asList(user1, user2, user3, user4, user5);
    }

    @Test
    public void whenTestAllAttributesThenResult() {
        Analize.User changed1 = new Analize.User(user1.getId(), "Petr");
        Analize.User changed2 = new Analize.User(user2.getId(), "Viktor");
        Analize.User addedUser1 = new Analize.User(144, "Anna");
        Analize.User addedUser2 = new Analize.User(18, "Andrey");
        Analize.User addedUser3 = new Analize.User(1, "Art");
        List<Analize.User> current = Arrays.asList(changed1, changed2, addedUser1, addedUser2, addedUser3, user3, user4);
        Analize.Info info = Analize.diff(previous, current);
        assertThat(info.deleted, is(1));
        assertThat(info.changed, is(2));
        assertThat(info.added, is(3));

    }
}
