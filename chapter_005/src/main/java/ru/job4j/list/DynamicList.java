package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class DynamicList<E> implements Iterable {

    /**
     * count elements.
     */
    private int size = 0;

    /**
     * Modification count.
     */
    int modCount = 0;

    private Node<E> first;
    private Node<E> last;

    public void add(E value) {
        Node<E> newValue = new Node<>(value);
        if (size == 0) {
            first = newValue;
            last = newValue;
        } else {
        last.next = newValue;
        newValue.previous = last;
        last = newValue;
        }
        modCount++;
        size++;
    }

    public E get(int index) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (index < 0) {
            throw new IllegalArgumentException();
        } else if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<E>() {
            private int count = 0;
            private final int mod = modCount;
            Node<E> temp = first;

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
            public E next() {
                checkException();
                E result = temp.date;
                temp = temp.next;
                count++;
                return result;
            }
        };
    }
    private static class Node<E> {
        final E date;
        /**
         * next Node
         */
        Node<E> next;
        /**
         * previous Node
         */
        Node<E> previous;

        Node(E date) {
            this.date = date;
        }
    }
}
