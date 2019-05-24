package ru.job4j.monitore;

import java.util.Objects;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class User {
    private final int id;
    private int ammount;

    public User(int id, int ammount) {
        this.id = id;
        this.ammount = ammount;
    }

    public int getId() {
        return id;
    }

    public int getAmmount() {
        return ammount;
    }

    public void incrementAmmount(int increment) {
        this.ammount += increment;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && ammount == user.ammount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ammount);
    }
}
