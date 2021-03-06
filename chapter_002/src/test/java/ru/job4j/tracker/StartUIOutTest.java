package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StartUIOutTest {
    /**
     * Строка меню.
     */
    private final String menu = new StringJoiner(System.lineSeparator())
            .add("0 - Добавить заявку")
            .add("1 - Показать все заявки")
            .add("2 - Изменить заявку")
            .add("3 - Удалить заявку")
            .add("4 - Найти заявку по ID")
            .add("5 - Найти заявку по имени")
            .add("6 - ВЫХОД")
            .toString();

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final String h = "Hello";


    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };

   // /**
   //  * Замена дефолтного потока вывода.
   //  */
   //@Before
   //public void loadOutput() {
   //    System.out.println("execute before method");
   //    System.setOut(new PrintStream(this.out));
   //}

   ///**
   // * Установка потоку вывода дефолтное значение.
   // */
   //@After
   //public void backOutput() {
   //    System.setOut(System.out);
   //    System.out.println("execute after method");
   // }
    @Test
    public void whenShowAllThenShow() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "desc first");
        Item second = new Item("second", "desc second");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(
                output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu)
                                .add("------------ Список заявок: --------------")
                                .add("------------ Заявка № 1: --------------")
                                .add("")
                                .add("The Item name: first,")
                                .add("description: desc first,")
                                .add("id: " + first.getId())
                                .add("")
                                .add("------------ Заявка № 2: --------------")
                                .add("")
                                .add("The Item name: second,")
                                .add("description: desc second,")
                                .add("id: " + second.getId())
                                .add("")
                                .add("------------ Конец списка заявок --------------")
                                .add(menu)
                                .add("")
                                .toString()
                ));

    }
    @Test
    public void whenFindItemByIdThenShowIt() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "desc first");
        Item second = new Item("second", "desc second");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"4", second.getId(), "6"});
        new StartUI(input, tracker, output).init();
        assertThat(
                output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu)
                                .add("------------ Поиск заявки: --------------")
                                .add("The Item name: second,")
                                .add("description: desc second,")
                                .add("id: " + second.getId())
                                .add("")
                                .add(menu)
                                .add("")
                                .toString()
                ));

    }
    @Test
    public void whenFindItemByNameThenShow() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "desc first");
        Item second = new Item("second", "desc second");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"5", "first", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(
                output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu)
                                .add("------------ Поиск заявки: --------------")
                                .add("The Item name: first,")
                                .add("description: desc first,")
                                .add("id: " + first.getId())
                                .add("")
                                .add(menu)
                                .add("")
                                .toString()
                ));

    }
    @Test
    public void whenFindItemByIncorrectNameThenNothingShow() {
        Tracker tracker = new Tracker();
        Item first = new Item("first", "desc first");
        Item second = new Item("second", "desc second");
        tracker.add(first);
        tracker.add(second);
        Input input = new StubInput(new String[]{"5", "third", "6"});
        new StartUI(input, tracker, output).init();
        assertThat(
                output.toString(),
                is(
                        new StringJoiner(System.lineSeparator())
                                .add(menu)
                                .add("------------ Поиск заявки: --------------")
                                .add("Не удалось найти заявки с указанными именами")
                                .add(menu)
                                .add("")
                                .toString()
                ));

    }
}
