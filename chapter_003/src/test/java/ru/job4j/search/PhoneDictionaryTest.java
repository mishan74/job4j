package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
    @Test
    public void whenFindBySurnameAndAddress() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
        new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        var persons = phones.find("n");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
    @Test
    public void whenFindByPhone() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        var persons = phones.find("53");
        assertThat(persons.size(), is(2));
    }
    @Test
    public void whenNotFind() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        var persons = phones.find("Z");
        assertThat(persons.isEmpty(), is(true));
    }
}