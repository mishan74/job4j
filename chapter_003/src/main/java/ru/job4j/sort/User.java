package ru.job4j.sort;

/**
 * @author Mikhail.rozdin.
 * @version $id$.
 * @since 0.1.
 */
public class User implements Comparable<User> {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        final int compareName = this.name.compareTo(o.name);
        return compareName != 0 ? compareName : Integer.compare(this.age, o.age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
