package ru.job4j.set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */


public class Unique {
    /**
     * Проверка уникальности символов строк
     */
    public boolean isUnique(String first, String sub) {
        boolean result = true;
        Map<Character, Integer> temp = new HashMap<>();
        for (char c : first.toCharArray()) {
            temp.merge(c, 1, Integer::sum);
            // temp.computeIfPresent(c, (k, v) -> ++v);
            // temp.putIfAbsent(c, 1);
        }
        for (char c : sub.toCharArray()) {
            Integer n = temp.merge(c, -1, Integer::sum);
            if (n < 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}