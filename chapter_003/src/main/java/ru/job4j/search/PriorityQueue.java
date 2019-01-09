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
       if (tasks.isEmpty()) {
           tasks.add(task);
       } else {
           for (Task temp : tasks) {
               if (task.compareTo(temp) <= 0) {
                   tasks.add(tasks.indexOf(temp), task);
                   break;
               }
           }
           tasks.addLast(task);
       }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
