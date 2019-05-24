package ru.job4j.monitore;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class UserStorageTest {


    @Test
    public void whenAddSomeUserThenTrue() {
        UserStorage us = new UserStorage();
        boolean exp1 = us.add(new User(1, 1500));
        boolean exp2 = us.add(new User(2, 200));

        assertTrue(exp1);
        assertTrue(exp2);
    }

    @Test
    public void whenDeleteUsersThenTrue() {
        UserStorage us = new UserStorage();
        User u = new User(1, 1500);
        us.add(u);
        boolean exp1 = us.delete(u);

        assertTrue(exp1);
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        UserStorage us = new UserStorage();
        User from = new User(1, 500);
        User to = new User(2, 500);
        us.add(from);
        us.add(to);
        us.transfer(1, 2, 100);

        assertThat(from.getAmmount(), is(400));
        assertThat(to.getAmmount(), is(600));
    }

    @Test
    public void whenUpdateUserThenTransferTrue() {
        UserStorage us = new UserStorage();
        User user = new User(1, 500);
        User newUser = new User(1, 2500);
        User to = new User(2, 500);
        us.add(user);
        us.add(to);
        boolean exp1 = us.update(newUser);

        boolean exp2 = us.transfer(1, 2, 2500);

        assertTrue(exp1);
        assertTrue(exp2);
        assertThat(newUser.getAmmount(), is(0));
        assertThat(to.getAmmount(), is(3000));
    }
}
