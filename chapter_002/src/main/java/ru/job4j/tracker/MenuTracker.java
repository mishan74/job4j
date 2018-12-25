package ru.job4j.tracker;

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
    private final UserAction[] actions = new UserAction[7];

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
        return this.actions.length;
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        actions[0] = new AddItem();
        actions[1] = new ShowItems();
        actions[2] = new EditItem();
        actions[3] = new DeleteItem();
        actions[4] = new FindItemById();
        actions[5] = new FindItemsByName();
        actions[6] = new ExitProgram();
    }
    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
    private class AddItem implements UserAction {
        @Override
        public String key() {
            return ADD;
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

        @Override
        public String info() {
            return "0 - Добавить заявку";
        }
    }
    private class DeleteItem implements UserAction {
        @Override
        public String key() {
            return DELETE;
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

        @Override
        public String info() {
            return "3 - Удалить заявку";
        }
    }
    private class EditItem implements UserAction {
        @Override
        public String key() {
            return EDIT;
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

        @Override
        public String info() {
            return "2 - Изменить заявку";
        }
    }
    private class FindItemById implements UserAction {
        @Override
        public String key() {
            return FIND_ID;
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

        @Override
        public String info() {
            return "4 - Найти заявку по ID";
        }
    }
    private class FindItemsByName implements UserAction {
        @Override
        public String key() {
            return FIND_NAME;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки: --------------");
            String name = input.ask("Введите имя заявки");
            Item[] items = tracker.findByName(name);
            if (items.length != 0) {
                for (Item item : items) {
                    System.out.println(item);
                }
            } else {
                System.out.println("Не удалось найти заявки с указанными именами");
            }
        }

        @Override
        public String info() {
            return "5 - Найти заявку по имени";
        }
    }
    private class ShowItems implements UserAction {
        @Override
        public String key() {
            return SHOW;
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

        @Override
        public String info() {
            return "1 - Показать все заявки";
        }
    }
    private class ExitProgram implements UserAction {
        @Override
        public String key() {
            return EXIT;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return "6 - ВЫХОД";
        }
    }
}