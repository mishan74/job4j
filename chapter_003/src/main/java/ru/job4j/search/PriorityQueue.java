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
        int count = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getPriority() - tasks.get(i).getPriority() <= 0) {
                count = i;
                break;
            }
        }
        if (count < 0) {
            tasks.addLast(task);
        } else {
            tasks.add(count, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
