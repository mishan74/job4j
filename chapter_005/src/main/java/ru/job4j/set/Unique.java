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
            Integer n = temp.get(c);
            if (n == null) {
                temp.put(c, 1);
            } else {
                temp.put(c, ++n);
            }
        }
        for (char c : sub.toCharArray()) {
            Integer n = temp.get(c);
            if (n == null || n <= 0) {
                result = false;
                break;
            }
            temp.put(c, --n);
        }
        return result;
    }
}
    //int checkSize;
    //Set<Character> uniqueSet = new HashSet<>();
    //    for (char a : first.toCharArray()) {
    //            uniqueSet.add(a);
    //            }
    //            checkSize = uniqueSet.size();
    //            for (char a : second.toCharArray()) {
    //            uniqueSet.add(a);
    //            }
    //            return checkSize == uniqueSet.size();