package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / (double) rows);
        int[][] array = new int[rows][cells];

        int r = 0;
        int c = 0;
        for (Integer integer : list) {
            array[r][c] = integer;
            if (c >= cells - 1) {
                c = 0;
                r++;
            } else {
                c++;
            }
        }
        return array;
    }
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] ints : list) {
            for (int temp : ints) {
                result.add(temp);
            }
        }
        return result;
    }
}
