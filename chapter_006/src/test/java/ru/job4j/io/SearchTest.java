package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SearchTest {
    String path;
    final String separator = File.separator;
    @Before
    public void init() {
        path = System.getProperty("java.io.tmpdir");
        System.out.println(path);

        File temp = new File(path + separator + "javatest");
        boolean dir1 = temp.mkdir();
        temp = new File(path + separator + "javatest" + separator + "test");
        boolean dir2 = temp.mkdir();
        try (
                FileOutputStream one = new FileOutputStream(path + separator + "javatest" + separator + "test" + separator + "one.txt");
                FileOutputStream two = new FileOutputStream(path + separator + "javatest" + separator + "test" + separator + "two.rtf");
                FileOutputStream six = new FileOutputStream(path + separator + "javatest" + separator + "six.jpeg")

        ) {
            one.write("Text".getBytes());
            two.write("Text".getBytes());
            six.write("Text".getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenFilterSearchThenResult() {
        Search search = new Search();
        List<String> check = Arrays.asList(".txt", ".jpeg");
        List<File> files = search.files(path + separator + "javatest", check);
        List<String> expected = Arrays.asList(
                 "six.jpeg", "one.txt"
        );
        List<String> result = new ArrayList<>();
        for (File file : files) {
           result.add(file.getName());
        }

        assertThat(result, is(expected));
    }
}