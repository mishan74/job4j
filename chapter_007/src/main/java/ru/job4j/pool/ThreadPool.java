package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private Thread producer;

    private void initThreads() {
        IntStream
                .range(0, size)
                .forEach(value -> threads.add(new Thread(() -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            tasks.poll().run();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                })));
    }

    private void initProducer() {
        this.producer = new Thread(()-> {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.work(() -> System.out.println(Thread.currentThread().getName() + " is running"));
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } });
    }

    private void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void start() {
        this.initThreads();
        this.initProducer();
        threads.forEach(Thread::start);
        producer.start();

    }

    public void shutdown() {
        producer.interrupt();
        threads.forEach(v -> {
            try {
                v.interrupt();
                v.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.start();
        Thread.sleep(10000);
        threadPool.shutdown();
    }
}

