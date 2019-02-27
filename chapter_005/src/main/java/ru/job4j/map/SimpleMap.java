package ru.job4j.map;


import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleMap<K, V> implements Iterable {
    private static class Node<K, V> implements IterNode<K, V> {
        final K key;
        final int hash;
        final V value;
        public V getValue() {
            return this.value;
        }
        public K getKey() {
            return this.key;
        }

        Node(K key, int hash, V value) {
            this.value = value;
            this.key = key;
            this.hash = hash;
        }
    }
    private int modCount;
    private Node<K, V>[] table;
    private int size;

    private static final double LOAD_FACTOR = 0.5;

    private static final int DEFAULT_CAPACITY = 6;

    public int getTableLength() {
        return table.length;
    }

    private int getSize() {
        return size;
    }
    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private boolean checkLength() {
        return getSize() >= LOAD_FACTOR * getTableLength();
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];
        for (Node<K, V> kvNode : oldTable) {
            if (kvNode != null) {
            this.insert(kvNode.key, kvNode.value);
            }
        }
    }

    public SimpleMap() {
        this.table = new Node[DEFAULT_CAPACITY];
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int i = hash(key) == 0 ? 0 : hash(key) & (getTableLength() - 1);
       // System.out.println(i);
        if (checkLength()) {
            resize();
        }
        if (table[i] == null) {
            table[i] = new Node(key, hash(key), value);
            size++;
            result = true;
            modCount++;
        }

        return result;
    }
    public V get(K key) {
        Node<K, V> temp = table[hash(key) % this.getTableLength()];
        return temp == null ? null : temp.value;
    }
    public boolean delete(K key) {
        boolean result = false;
        int i = hash(key) % this.getTableLength();
        Node<K, V> temp = table[i];
        if (temp != null) {
            table[i] = null;
            result = true;
            modCount++;
        }
        return result;
    }


    @Override
    public Iterator<IterNode<K, V>> iterator() {
        return new Iterator<>() {
            private Node<K, V> node;
            private int index = 0;
            private final int mod = modCount;

            private void checkException() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkException();
                boolean result = false;
                int temp = IntStream
                        .range(index, table.length)
                        .filter(x-> table[x] != null)
                        .findAny()
                        .orElse(-1);
                if (temp >= 0) {
                    index = temp;
                    result = true;
                }
                return result;
            }

            @Override
            public Node<K, V> next() {
                checkException();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++];
            }
        };
    }
}