package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {

    /**
     * хранит ссылку на объект .
     */
    private final Input input;
    /**
     * хранит ссылку на объект .
     */
    private final ITracker tracker;

    private final Consumer<String> output;
    /**
     * хранит ссылку на массив типа UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *  @param input   объект типа Input
     * @param tracker объект типа Tracker
     * @param output вывод.
     */
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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
                output.accept(action.info());
            }
        }
    }
    private class AddItem extends BaseAction {
        protected AddItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }
    }
    private class DeleteItem extends BaseAction {

        protected DeleteItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Изменение заявки: --------------");
            String id = input.ask("Введите id заявки, которую хотите удалить");
            if (tracker.delete(id)) {
                output.accept("------------ Заявка успешно удалена --------------");
            } else {
                output.accept("------------ Не удалось удалить заявку --------------");
            }
        }
    }
    private class EditItem extends BaseAction {
        protected EditItem(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Изменение заявки: --------------");
            String id = input.ask("Введите id заявки, которую хотите изменить");
            String name = input.ask("Введите новое имя заявки");
            String desc = input.ask("Введите новое описание заявки");
            Item item = new Item(name, desc);
            if (tracker.replace(id, item)) {
                output.accept("------------ Заявка успешно изменена --------------");
            } else {
                output.accept("------------ Не удалось изменить заявку --------------");
            }
        }
    }
    private class FindItemById extends BaseAction {
        protected FindItemById(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Поиск заявки: --------------");
            String id = input.ask("Введите id заявки");
            Item item = tracker.findById(id);
            if (item == null) {
                output.accept("Не удалось найти заявку с указанным id");
            } else {
                output.accept(item.toString());
            }
        }
    }
    private class FindItemsByName extends BaseAction {
        protected FindItemsByName(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Поиск заявки: --------------");
            String name = input.ask("Введите имя заявки");
            List<Item> items = tracker.findByName(name);
            if (items.size() != 0) {
                for (Item item : items) {
                    output.accept(item.toString());
                }
            } else {
                output.accept("Не удалось найти заявки с указанными именами");
            }
        }
    }
    private class ShowItems extends BaseAction {
        protected ShowItems(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Список заявок: --------------");
            int counter = 0;
            for (Item item : tracker.findAll()) {
                output.accept(String.format("------------ Заявка № %d: --------------%s", ++counter, System.lineSeparator()));
                output.accept(item.toString());
            }
            output.accept("------------ Конец списка заявок --------------");
        }
    }
    private class ExitProgram extends BaseAction {
        protected ExitProgram(String key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {

        }
    }
}