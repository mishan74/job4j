package ru.job4j.map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public interface IterNode<K, V> {
    K getKey();
    V getValue();
}
