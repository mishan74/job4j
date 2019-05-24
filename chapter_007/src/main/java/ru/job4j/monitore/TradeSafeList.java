package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */

@ThreadSafe
public class TradeSafeList<E> implements Iterable {

    @GuardedBy("this")
    private final DynamicList<E> dynamicList;

    public TradeSafeList(DynamicList<E> dynamicList) {
        this.dynamicList = dynamicList;
    }

    public synchronized void add(E value) {
        dynamicList.add(value);
    }

    public synchronized E get(int index) {
        return dynamicList.get(index);
    }

    @Override
    public synchronized Iterator iterator() {
        return copy(this.dynamicList).iterator();
    }

    private DynamicList<E> copy(DynamicList<E> dynamicList) {
        DynamicList<E> result = new DynamicList<>();
        if (dynamicList != null) {
            for (Object o : dynamicList) {
                result.add((E) o);
            }
        }
        return result;
    }
}