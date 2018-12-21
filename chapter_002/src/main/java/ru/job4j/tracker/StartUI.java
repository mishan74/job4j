package ru.job4j.tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для показа всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для изменения заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_ID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FIND_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ID.equals(answer)) {
                this.findIdItem();
            } else if (FIND_NAME.equals(answer)) {
                this.findNameItems();
            } else if (EXIT.equals(answer)) {
                exit = true;
            } else {
                System.out.println("Некорректный ввод, попробуйте еще раз");
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Отображает список всех заявок.
     */
    private void showItems() {
        System.out.println("------------ Список заявок: --------------");
        int counter = 0;
        for (Item item : this.tracker.findAll()) {
            System.out.printf("------------ Заявка № %d: --------------%s", ++counter, System.lineSeparator());
            System.out.println(item);
        }
        System.out.println("------------ Конец списка заявок --------------");
    }

    /**
     * Метод редактирования имени и описания заявки.
     */
    private void editItem() {
        System.out.println("------------ Изменение заявки: --------------");
        String id = this.input.ask("Введите id заявки, которую хотите изменить");
        String name = this.input.ask("Введите новое имя заявки");
        String desc = this.input.ask("Введите новое описание заявки");
        Item item = new Item(name, desc);
        if (this.tracker.replace(id, item)) {
            System.out.println("------------ Заявка успешно изменена --------------");
        } else {
            System.out.println("------------ Не удалось изменить заявку --------------");
        }

    }

    /**
     * Метод удаления заявки
     */
    private void deleteItem() {
        System.out.println("------------ Изменение заявки: --------------");
        String id = this.input.ask("Введите id заявки, которую хотите удалить");
        if (this.tracker.delete(id)) {
            System.out.println("------------ Заявка успешно удалена --------------");
        } else {
            System.out.println("------------ Не удалось удалить заявку --------------");
        }
    }

    /**
     * Метод поиска заявки по id.
     */
    private void findIdItem() {
        System.out.println("------------ Поиск заявки: --------------");
        String id = this.input.ask("Введите id заявки");
        Item item = this.tracker.findById(id);
        if (item == null) {
            System.out.println("Не удалось найти заявку с указанным id");
        } else {
            System.out.println(item);
        }
    }

    /**
     * Метод поиска и вывода на экран заявок по имени.
     */
    private void findNameItems() {
        System.out.println("------------ Поиск заявки: --------------");
        String name = this.input.ask("Введите имя заявки");
        Item[] items = this.tracker.findByName(name);
        if (items.length != 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Не удалось найти заявки с указанными именами");
        }

    }

    /**
     * Основное меню программы.
     * Выводится в консоль.
     */
    private void showMenu() {
        String ls = System.lineSeparator();
        String sb = "Меню" + ls
                + "0 - Добавить заявку" + ls
                + "1 - Показать все заявки" + ls
                + "2 - Изменить заявку" + ls
                + "3 - Удалить заявку" + ls
                + "4 - Найти заявку по ID" + ls
                + "5 - Найти заявку по имени" + ls
                + "6 - ВЫХОД";
        System.out.println(sb);

    }

    /**
     * Запускт программы.
     * @param args аргументы запуска программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
