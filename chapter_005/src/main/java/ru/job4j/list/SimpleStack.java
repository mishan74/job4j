package ru.job4j.list;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> {

    private final SimpleArrayList simpleArrayList;

    public SimpleStack(SimpleArrayList simpleArrayList) {
        this.simpleArrayList = simpleArrayList;
    }

    public T poll() {
        return (T) simpleArrayList.delete();
    }
    public void push(T value) {
        simpleArrayList.add(value);
    }
    public int getSize() {
        return simpleArrayList.getSize();
    }
}
