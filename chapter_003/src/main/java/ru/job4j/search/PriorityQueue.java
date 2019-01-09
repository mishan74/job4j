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
        int count = 0;
        for (Task temp : tasks) {
            if (task.getPriority() < temp.getPriority()) {
                break;
            }
            count++;
        }
        tasks.add(count, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
