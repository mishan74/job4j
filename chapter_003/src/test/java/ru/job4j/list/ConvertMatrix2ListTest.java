package ru.job4j.list;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

        @Test
        public void when4on4ArrayThenList4() {
            ConvertMatrix2List list = new ConvertMatrix2List();
            int[][] input = {
                    {1, 2, 8, 9},
                    {3, 4, 5, 6},
                    {36, 41, 52, 63},
                    {93, 54, 25, 16},
            };
            List<Integer> expect = Arrays.asList(
                    1, 2, 8, 9, 3, 4, 5, 6, 36, 41, 52, 63, 93, 54, 25, 16
            );
            List<Integer> result = list.toList(input);
            assertThat(result, is(expect));
        }
    @Test
    public void when0ArrayThenList0() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {0},
                {0},
        };
        List<Integer> expect = Arrays.asList(
               0, 0
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}