package ru.job4j.search;

import java.util.LinkedList;


public class PriorityQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определяется по полю приоритет.
     * Для вставки использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
           for (int i = 0; i < tasks.size(); i++) {
               if (task.compareTo(tasks.get(i)) <= 0) {
                   tasks.add(i, task);
                   break;
               }
           }
           tasks.addLast(task);
       }

    public Task take() {
        return this.tasks.poll();
    }
}
