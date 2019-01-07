package ru.job4j.search;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        List<Person> persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
    @Test
    public void whenFindBySurnameAndAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
        new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        List<Person> persons = phones.find("n");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        List<Person> persons = phones.find("53");
        assertThat(persons.size(), is(2));
    }
    @Test
    public void whenNotFind() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        phones.add(
                new Person("Viltor", "Plyshev", "534872", "Moscow")
        );
        List<Person> persons = phones.find("Z");
        assertThat(persons.isEmpty(), is(true));
    }
}