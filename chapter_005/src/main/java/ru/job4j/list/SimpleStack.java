package ru.job4j.list;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> extends SimpleArrayList {

    public T poll() {
        return (T) delete();
    }
    public void push(T value) {
        add(value);
    }
}
