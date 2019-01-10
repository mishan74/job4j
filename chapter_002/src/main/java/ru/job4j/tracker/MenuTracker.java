package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {

    /**
     * хранит ссылку на объект .
     */
    private final Input input;
    /**
     * хранит ссылку на объект .
     */
    private final Tracker tracker;
    /**
     * хранит ссылку на массив типа UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        actions.add(new AddItem(UserAction.ADD, "Добавить заявку"));
        actions.add(new ShowItems(UserAction.SHOW, "Показать все заявки"));
        actions.add(new EditItem(UserAction.EDIT, "Изменить заявку"));
        actions.add(new DeleteItem(UserAction.DELETE, "Удалить заявку"));
        actions.add(new FindItemById(UserAction.FIND_ID, "Найти заявку по ID"));
        actions.add(new FindItemsByName(UserAction.FIND_NAME, "Найти заявку по имени"));
        actions.add(new ExitProgram(UserAction.EXIT, "ВЫХОД"));
    }
    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
    private class AddItem extends BaseAction {
        protected AddItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }
    }
    private class DeleteItem extends BaseAction {

        protected DeleteItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение заявки: --------------");
            String id = input.ask("Введите id заявки, которую хотите удалить");
            if (tracker.delete(id)) {
                System.out.println("------------ Заявка успешно удалена --------------");
            } else {
                System.out.println("------------ Не удалось удалить заявку --------------");
            }
        }
    }
    private class EditItem extends BaseAction {
        protected EditItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение заявки: --------------");
            String id = input.ask("Введите id заявки, которую хотите изменить");
            String name = input.ask("Введите новое имя заявки");
            String desc = input.ask("Введите новое описание заявки");
            Item item = new Item(name, desc);
            if (tracker.replace(id, item)) {
                System.out.println("------------ Заявка успешно изменена --------------");
            } else {
                System.out.println("------------ Не удалось изменить заявку --------------");
            }
        }
    }
    private class FindItemById extends BaseAction {
        protected FindItemById(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки: --------------");
            String id = input.ask("Введите id заявки");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("Не удалось найти заявку с указанным id");
            } else {
                System.out.println(item);
            }
        }
    }
    private class FindItemsByName extends BaseAction {
        protected FindItemsByName(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки: --------------");
            String name = input.ask("Введите имя заявки");
            List<Item> items = tracker.findByName(name);
            if (items.size() != 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("Не удалось найти заявки с указанными именами");
            }
        }
    }
    private class ShowItems extends BaseAction {
        protected ShowItems(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Список заявок: --------------");
            int counter = 0;
            for (Item item : tracker.findAll()) {
                System.out.printf("------------ Заявка № %d: --------------%s", ++counter, System.lineSeparator());
                System.out.println(item);
            }
            System.out.println("------------ Конец списка заявок --------------");
        }
    }
    private class ExitProgram extends BaseAction {
        protected ExitProgram(String key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {

        }
    }
}