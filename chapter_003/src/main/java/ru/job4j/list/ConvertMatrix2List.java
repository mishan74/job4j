package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
       //List<Integer> list = new ArrayList<>();
       //for (int[] ints : array) {
       //    for (int anInt : ints) {
       //        list.add(anInt);
       //    }
       //}
       //return list;
        return Stream.of(array)
                .flatMapToInt(Arrays::stream)
                .boxed()
                .collect(Collectors.toList());
    }
}
