package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class NodeTest {

    Node first;
    Node two;
    Node third;
    Node four;

    @Before
    public void init() {
        first = new Node(1);
        two = new Node(2);
        third = new Node(3);
        four = new Node(4);

       first.next = two;
       two.next = third;
       third.next = four;
       four.next = first;

    }
    @Test
    public void whenCycleThenTrue() {
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenCycleInCenterThenTrue() {
        third.next = two;
        assertThat(Node.hasCycle(first), is(true));
    }

    @Test
    public void whenNoCycleThenFalse() {
        four.next = null;
        assertThat(Node.hasCycle(first), is(false));
    }

}
