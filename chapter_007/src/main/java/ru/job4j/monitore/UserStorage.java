package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (user != null && !users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            result = true;
        }
        return result;
    }
    public synchronized boolean update(User user) {
        boolean result = false;
        if (user != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            result = true;
        }
        return result;
    }
    public synchronized boolean delete(User user) {
        boolean result = false;
        if (user != null && users.containsKey(user.getId())) {
            users.remove(user.getId());
            result = true;
        }
        return result;
    }
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (users.containsKey(fromId)
                && users.containsKey(toId)
                && (users.get(fromId).getAmmount() >= amount)) {
            users.get(fromId).incrementAmmount(-amount);
            users.get(toId).incrementAmmount(amount);

            result = true;
        }
        return result;
    }

}

