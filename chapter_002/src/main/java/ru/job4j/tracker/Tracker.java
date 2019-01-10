package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>(100);

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        if (item != null) {
            item.setId(this.generateId());
            items.add(item);
        }
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() * Math.random());
    }

    /**
     * Метод заменяет заявку.
     * @param id номер заявки, которую нужно заменить.
     * @param item новая заявка.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int count = 0;
        for (Item temp : items) {
            if (temp.getId().equals(id)) {
                item.setId(id);
                items.set(count, item);
                result = true;
                break;
            }
            count++;
        }
        return result;
    }

    /**
     * Метод удаляет текущую заявку.
     * Оставшиеся заявки смещаются по индексу на единицу влево.
     * @param id номер удаляемой заявки.
     */
    public boolean delete(String id) {
        boolean result = false;
        int count = 0;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                items.remove(count);
                result = true;
                break;
            }
            count++;
        }
        return result;
    }

    /**
     * Метод генерирует массив всех заполненных элементов item.
     * @return Массив с элементами item, без null элементов.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Возвращает элементы, найденные по имени.
     * @param key имя заявки.
     * @return массив, с найденными именами заявок.
     */
    public List<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : this.findAll()) {
            if (item.getName().equals(key)) {
               result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод возвращает найденную заявку по id.
     * @param id номер заявки
     * @return найденная заявка, или null в случае ее отсутствия.
     */
    public Item findById(String id) {
       Item result = null;
            for (Item item : this.findAll()) {
                if (item.getId().equals(id)) {
                    result = item;
                    break;
                }
            }
       return result;
    }
}