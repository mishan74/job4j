package ru.job4j.array;

import java.util.Arrays;

public class ArrayDuplicate {

    public String[] remove(String[] array) {
        int duplicate = 0;
        boolean repeat;
        do {
            repeat = false;
            for (int out = 0; out < array.length - duplicate; out++) {
                for (int in = out + 1; in < array.length - duplicate; in++) {
                    if (array[out].equals(array[in])) {
                        for (int i = in; i < array.length - 1 - duplicate; i++) {
                            String temp = array[i];
                            array[i] = array[i + 1];
                            array[i + 1] = temp;
                        }
                        duplicate++;
                        repeat = true;
                    }
                }
            }
        } while (repeat);
    return Arrays.copyOf(array, array.length - duplicate);
    }
}
