package ru.job4j.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
@Override
public int compare(String left, String right) {
        int result = 0;
        char[] charLeft = left.toCharArray();
        char[] charRight = right.toCharArray();
        int lengthL = charLeft.length;
        int lengthR = charRight.length;
        int count =  Math.min(lengthL, lengthR);
        for (int i = 0; i < count; i++) {
            if (charLeft[i] != charRight[i]) {
                result = charLeft[i] - charRight[i];
                return result;
            }
        }
        result = lengthL - lengthR;
        return result;
    }
}
