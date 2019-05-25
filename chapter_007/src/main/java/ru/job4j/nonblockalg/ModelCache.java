package ru.job4j.nonblockalg;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */

public class ModelCache {

    private final ConcurrentHashMap<Integer, Base> hashMap = new ConcurrentHashMap<>();

    public Base add(Base model) {
        return hashMap.putIfAbsent(model.getId(), model);
    }

    public void update(Base model) {
        hashMap.computeIfPresent(model.getId(),
                (k, v) -> {
                    if (model.getVersion() != v.getVersion()) {
                        throw new OptimisticException("Non correct version");
                    }
                    v.incrementVersion();
                    return v;
                });
    }

    public boolean delete(Base model) {
        return hashMap.remove(model.getId(), model);
    }

}
