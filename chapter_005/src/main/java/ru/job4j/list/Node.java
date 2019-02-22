package ru.job4j.list;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Node<T> {
    final T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }

   static boolean hasCycle(Node first) {
        Node turtle = first;
        Node rabbit = first;
        boolean result = false;

        while (rabbit.next != null && rabbit.next.next != null) {
            turtle = turtle.next;
            rabbit = rabbit.next.next;
            if (turtle == rabbit) {
                result = true;
                break;
            }
        }
        return result;
    }
}
