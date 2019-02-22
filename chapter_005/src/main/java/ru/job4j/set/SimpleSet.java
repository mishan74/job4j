package ru.job4j.set;

import ru.job4j.list.DynamicArray;

import java.util.Iterator;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<E> implements Iterable {
    private final DynamicArray<E> dynamicArray;

    public SimpleSet() {
        this.dynamicArray = new DynamicArray();
    }

    public void add(E value) {
        boolean added = true;
        for (Object o : dynamicArray) {
            if (o.equals(value)) {
                added = false;
                break;
            }
        }
       if (added) {
           dynamicArray.add(value);
       }
    }

    public Iterator iterator() {
        return dynamicArray.iterator();
    }
}
