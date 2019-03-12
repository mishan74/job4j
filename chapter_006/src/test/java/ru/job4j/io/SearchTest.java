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
    @Before
    public void init() {
        path = System.getProperty("java.io.tmpdir");
        System.out.println(path);

        File temp = new File(path + "\\javatest");
        boolean dir1 = temp.mkdir();
        temp = new File(path + "\\javatest\\test");
        boolean dir2 = temp.mkdir();
        try (
                FileOutputStream one = new FileOutputStream(path + "\\javatest\\test\\one.txt");
                FileOutputStream two = new FileOutputStream(path + "\\javatest\\test\\two.rtf");
                FileOutputStream six = new FileOutputStream(path + "\\javatest\\six.jpeg")

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
        List<File> files = search.files(path, check);
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