package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when14ElementsThen16() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14),
                4
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when0ElementsThen0() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Collections.singletonList(0),
                4
        );
        int[][] expect = {
                {0},
                {0},
                {0},
                {0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when2ArraysThenConvert() {
        ConvertList2Array convertList2Array = new ConvertList2Array();
        List<int[]> list = List.of(
        new int[]{1, 2},
        new int[]{3, 4, 5, 6}
        );
        List<Integer> result = convertList2Array.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }

    @Test
    public void when4ArraysThenConvert() {
        ConvertList2Array convertList2Array = new ConvertList2Array();
        List<int[]> list = List.of(
        new int[]{1, 2},
        new int[]{3, 4, 5, 6},
        new int[]{5, 4, 3, 2},
        new int[]{1}
        );
        List<Integer> result = convertList2Array.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1);
        assertThat(result, is(expect));
    }
}