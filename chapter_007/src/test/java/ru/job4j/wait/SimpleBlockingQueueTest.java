package ru.job4j.wait;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

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
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        for (int i = 0; i < 5; i++) {
                            queue.offer(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
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
