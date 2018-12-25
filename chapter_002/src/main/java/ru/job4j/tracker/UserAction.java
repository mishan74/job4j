package ru.job4j.tracker;

public interface UserAction {
    /**
     * Константа меню для добавления новой заявки.
     */
    String ADD = "0";

    /**
     * Константа меню для показа всех заявок.
     */
    String SHOW = "1";

    /**
     * Константа меню для изменения заявки.
     */
    String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    String FIND_ID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    String FIND_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    String EXIT = "6";

    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    String key();
    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    void execute(Input input, Tracker tracker);
    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}
