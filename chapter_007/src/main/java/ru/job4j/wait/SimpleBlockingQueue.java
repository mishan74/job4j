package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * initial first size
     */
    private int count = 0;

    /**
     * max size of queue
     */
    private static final int MAX_SIZE = 3;

    private boolean empty;
    private boolean full;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    /**
     * add
     * @param value element on the queue
     * @throws InterruptedException ex
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (count == MAX_SIZE) {
            this.wait();
        }
       this.queue.offer(value);
       this.notify();
       count++;
    }

    /**
     *
     * @return element from queue
     * @throws InterruptedException ex
     */
    public synchronized T poll() throws InterruptedException {
        while (count == 0) {
            this.wait();
        }
        count--;
        this.notify();
        return this.queue.poll();
    }
}