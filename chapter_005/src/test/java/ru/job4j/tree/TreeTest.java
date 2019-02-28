package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }
    @Test (expected = NoSuchElementException.class)
    public void whenIteratorThenIter() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Node<Integer>> iter = tree.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(4));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(5));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(6));
        assertThat(iter.hasNext(), is(false));
        int exception = iter.next().getValue();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenIteratorThenModifyException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Node<Integer>> iter = tree.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next().getValue(), is(2));
        tree.add(1, 4);
        boolean b = iter.hasNext();
    }
    @Test
    public void whenBinaryTreeThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenNotBinaryTreeThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(false));
    }
    @Test
    public void whenChildNotBinaryTreeThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(4, 5);
        tree.add(4, 6);
        tree.add(4, 7);
        assertThat(tree.isBinary(), is(false));
    }
}
