package ru.job4j.tree;

import java.util.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E>, Iterable<Node<E>> {

    private int modCount = 0;
    private final Node<E> root;
    public Tree(int root) {
        this.root = (Node<E>) new Node<>(root);
    }
    boolean isBinary() {
        boolean result = true;
        Iterator<Node<E>> iterator = iterator();
        while (iterator.hasNext()) {
            Node<E> temp = iterator.next();
            if (temp.leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }


    public Iterator<Node<E>> iterator() {

        return new Iterator<>() {
            private final int mod = modCount;
            Queue<Node<E>> data;
            {
                data = new LinkedList<>();
                data.offer(root);
            }

            private void checkException() {
                if (mod != modCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkException();
                return data.peek() != null;
            }

            @Override
            public Node<E> next() {
                checkException();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = this.data.poll();
                data.addAll(result.leaves());
                return result;
            }
        };
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        Optional<Node<E>> childNode = findBy(child);
        if (parentNode.isPresent() && childNode.isEmpty()) {
            parentNode.get().add(new Node<>(child));
            modCount++;
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }
}
