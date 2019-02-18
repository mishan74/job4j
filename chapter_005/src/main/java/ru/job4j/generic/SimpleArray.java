package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    final Object[] objects;
    int position = 0;

    public SimpleArray(int value) {
        this.objects = new Object[value];
    }

    public void add(T model) throws ArrayIndexOutOfBoundsException {
        checkException(position);
        objects[position++] = model;
    }

    public T set(int index, T model) throws ArrayIndexOutOfBoundsException {
        checkException(index);
        T result = null;
        int position = Arrays.asList(objects).indexOf(model);
        if (position != -1 && this.position > index) {
            objects[index] = model;
            remove(position);
            result = model;
        }
        return result;

    }

    @SuppressWarnings("unchecked")
    public T remove(int index) throws ArrayIndexOutOfBoundsException {
       checkException(index);
       T result = (T) this.objects[index];
       System.arraycopy(objects, index + 1, objects, index, objects.length - index - 1);
       objects[objects.length - 1] = null;
       return result;

    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws ArrayIndexOutOfBoundsException {
        checkException(index);
        return (T) this.objects[index];
    }

    private void checkException(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= objects.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return position > index;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) objects[index++];
            }
        };
    }

}