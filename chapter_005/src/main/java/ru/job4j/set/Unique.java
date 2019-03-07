package ru.job4j.set;

import java.util.HashSet;
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
    public boolean isUnique(String first, String second) {
        int checkSize;
        Set<Character> uniqueSet = new HashSet<>();
        for (char a : first.toCharArray()) {
            uniqueSet.add(a);
        }
        checkSize = uniqueSet.size();
        for (char a : second.toCharArray()) {
            uniqueSet.add(a);
        }
        return checkSize == uniqueSet.size();
    }
}
