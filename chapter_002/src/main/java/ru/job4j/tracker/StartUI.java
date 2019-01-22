package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    private final Consumer<String> output;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        menu.fillActions();
        int[] range = new int[menu.getActionsLength()];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        boolean exit = false;
        while (!exit) {
            menu.show();
            int point = input.ask("Выберете пункт меню:", range);
            if (point == 6) {
                exit = true;
            } else {
                menu.select(point);
            }
        }
    }

    /**
     * Запускт программы.
     * @param args аргументы запуска программы.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
