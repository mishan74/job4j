package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Mikhail Rozdin.
 * @version $id$
 * @since 0.1
 *
 * Class User contains description
 * simple user in 2 fields: name, passport.
 */
public class User {

    private String name;
    private String passport;


    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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
        return this.passport.equals(user.getPassport())
                && Objects.equals(this.name, user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.passport);
    }

}
