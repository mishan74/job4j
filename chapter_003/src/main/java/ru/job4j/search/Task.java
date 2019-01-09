package ru.job4j.search;

import java.util.Objects;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Task is a task manager.
 */
public class Task implements Comparable<Task> {
    private final String desc;
    private final int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task o) {
        return this.getPriority() - o.getPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return priority == task.priority
                && Objects.equals(desc, task.desc);
    }
}
