package ru.job4j.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map prevMap = new HashMap();
        previous.forEach(x -> prevMap.put(x.getId(), x.getName()));
        String temp;
        for (User user : current) {
            if (!prevMap.containsKey(user.id)) {
                info.added++;
                continue;
            }
            temp = (String) prevMap.remove(user.id);
            if (!temp.equals(user.getName())) {
                info.changed++;
            }
        }
        info.deleted = prevMap.size();
        return info;

    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Info {

        int added;
        int changed;
        int deleted;

    }
}
