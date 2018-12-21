package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Calendar;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

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
            this.items[this.position++] = item;
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
    public void replace(String id, Item item) {
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                item.setId(id);
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Метод удаляет текущую заявку.
     * Оставшиеся заявки смещаются по индексу на единицу влево.
     * @param id номер удаляемой заявки.
     */
    public void delete(String id) {
        for (int i = 0; i < position; i++) {
            if (items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, items.length - 1 - i);
                position--;
                break;
            }
        }
    }

    /**
     * Метод генерирует массив всех заполненных элементов item.
     * @return Массив с элементами item, без null элементов.
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Возвращает элементы, найденные по имени.
     * @param key имя заявки.
     * @return массив, с найденными именами заявок.
     */
    public Item[] findByName(String key) {
        int length = 0;
        Item[] result = new Item[position];
        for (Item item : this.findAll()) {
            if (item.getName().equals(key)) {
               result[length] = item;
               length++;
            }
        }
        return Arrays.copyOf(result, length);
    }

    /**
     * Метод возвращает найденную заявку по id.
     * @param id номер заявки
     * @return найденная заявка, или null в случае ее отсутствия.
     */
    public Item findById(String id) {
       Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
       return result;
    }
}