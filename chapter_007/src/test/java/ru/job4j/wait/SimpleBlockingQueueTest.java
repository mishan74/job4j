package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    @Test
    public void whenAddThenDeleteThenWait() throws InterruptedException {

        SimpleBlockingQueue<Integer> sb = new SimpleBlockingQueue<>();
        Consumer c = new Consumer(sb);
        Producer p = new Producer(sb);

        Thread consumer = new Thread(c);
        Thread producer = new Thread(p);

        consumer.start();
        producer.start();

        producer.join();
        consumer.join();

        int[] result = c.getArray();
        int[] expected = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expected));

    }
}
class Consumer implements Runnable {
    private final SimpleBlockingQueue sb;
    private final int[] array = new int[10];


    public Consumer(SimpleBlockingQueue sb) {
        this.sb = sb;
    }

    public int[] getArray() {
        return this.array;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                array[i] = (int) sb.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer implements Runnable {
    private final SimpleBlockingQueue sb;
    private int count = 0;


    public Producer(SimpleBlockingQueue sb) {
        this.sb = sb;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sb.offer(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
