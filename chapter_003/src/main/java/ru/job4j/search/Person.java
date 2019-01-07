package ru.job4j.search;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Класс Person представляет собой запись из телефонного справочника.
 */
public class Person {
    private final String name;
    private final String surname;
    private final String phone;
    private final String address;

    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
