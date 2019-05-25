package ru.job4j.nonblockalg;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ModelCacheTest {
    @Test
    public void whenRunTwoThreadAndIncrementThenException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        ModelCache cache = new ModelCache();
        cache.add(new Base(1, "John"));
        Thread thread1 = new Thread(
                () -> cache.update(new Base(1, "John")));

        Thread thread2 = new Thread(
                () -> {
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        cache.update(new Base(1, "John"));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("Non correct version"));
    }
}
