package ru.job4j.list;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<E> {
    final SimpleStack<E> forAdd;
    final SimpleStack<E> forDelete;

    public SimpleQueue() {
        forAdd = new SimpleStack<>(new SimpleArrayList());
        forDelete = new SimpleStack<>(new SimpleArrayList());
    }
    public E poll() {
        if (forDelete.getSize() == 0) {
            while (forAdd.getSize() > 0) {
                forDelete.push(forAdd.poll());
            }
        }
        return forDelete.poll();
    }

    public void push(E value) {
        forAdd.push(value);
    }
}