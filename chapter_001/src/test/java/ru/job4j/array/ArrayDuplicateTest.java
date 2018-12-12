package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] names = new String[]{"Veronica", "Smith", "Petr", "Smith", "Smith"};
        String[] expect = new String[]{"Veronica", "Smith", "Petr"};
        String[] result = ad.remove(names);
        assertThat(result, is(expect));
    }
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateSecondTest() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] names = new String[]{"Veronica", "Smith", "Petr", "Smith", "John"};
        String[] expect = new String[]{"Veronica", "Smith", "Petr", "John"};
        String[] result = ad.remove(names);
        assertThat(result, is(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateThirdTest() {
        ArrayDuplicate ad = new ArrayDuplicate();
        String[] names = new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expect = new String[]{"Привет", "Мир", "Супер"};
        String[] result = ad.remove(names);
        assertThat(result, is(expect));
    }
}

