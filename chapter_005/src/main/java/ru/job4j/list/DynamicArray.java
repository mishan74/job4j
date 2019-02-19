package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class DynamicArray<E> implements Iterable {
    /**
     * array for collect objects.
     */
    private Object[] objects;
    /**
     * count elements.
     */
    private int size = 0;
    /**
     * initial size of array.
     */
    private static final int INITIAL_CAPACITY = 10;

    /**
     * actual size of array.
     */
    private int capacity;

    /**
     * Modification count.
     */
    int modCount = 0;

    public DynamicArray(int newCapacity) throws IllegalArgumentException {
        if (newCapacity < 0) {
            throw new IllegalArgumentException();
        }
        capacity = newCapacity;
        this.objects = new Object[capacity];
    }

    public DynamicArray() {
       this(INITIAL_CAPACITY);
    }

    /**
     * Method added new element on the end of array.
     * @param value added element.
     */
    public void add(E value) {
        checkFull();
        objects[size++] = value;
        modCount++;
    }

    /**
     * Return value that is on the index.
     * @param index index of array.
     * @return value.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        return (E) objects[index];
    }

    private void checkFull() {
        if (size >= capacity) {
            capacity += capacity >> 1;
            objects = Arrays.copyOf(objects, capacity);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int count = 0;
            private final int mod = modCount;

            private void checkException() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
            @Override
            public boolean hasNext() {
                checkException();
                return size > this.count;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                checkException();
                return (E) objects[count++];
            }
        };
    }
}

