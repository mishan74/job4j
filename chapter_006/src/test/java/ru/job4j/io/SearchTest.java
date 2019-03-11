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
        temp.mkdir();
        temp = new File(path + "\\javatest\\test");
        temp.mkdir();
        temp = new File(path + "\\javatest\\test\\one");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\one\\two");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\one\\three");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\one//four");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\tempdir");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\tempdir\\one");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\tempdir\\two");
        temp.mkdir();
        temp  = new File(path + "\\javatest\\test\\tempdir\\two\\three");
        temp.mkdir();

        try (
                FileOutputStream one = new FileOutputStream(path + "\\javatest\\test\\one\\one.txt");
                FileOutputStream two = new FileOutputStream(path + "\\javatest\\test\\one\\two.rtf");
                FileOutputStream three = new FileOutputStream(path + "\\javatest\\test\\one\\three\\three.txt");
                FileOutputStream four = new FileOutputStream(path + "\\javatest\\test\\tempdir\\one\\four.txt");
                FileOutputStream five = new FileOutputStream(path + "\\javatest\\test\\five.txt");
                FileOutputStream six = new FileOutputStream(path + "\\javatest\\test\\tempdir\\two\\six.jpeg");
                FileOutputStream seven = new FileOutputStream(path + "\\javatest\\test\\tempdir\\two\\seven.txt");
                FileOutputStream eight = new FileOutputStream(path + "\\javatest\\test\\tempdir\\two\\three\\eight.txt");
                FileOutputStream nine = new FileOutputStream(path + "\\javatest\\test\\tempdir\\two\\three\\nine.rtf")

        ) {
            one.write("Text".getBytes());
            two.write("Text".getBytes());
            three.write("Text".getBytes());
            four.write("Text".getBytes());
            five.write("Text".getBytes());
            six.write("Text".getBytes());
            seven.write("Text".getBytes());
            eight.write("Text".getBytes());
            nine.write("Text".getBytes());
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
                "five.txt", "one.txt", "three.txt", "four.txt", "seven.txt", "six.jpeg", "eight.txt"
        );
        List<String> result = new ArrayList<>();
        for (File file : files) {
           result.add(file.getName());
        }
        assertThat(result, is(expected));
        File temp = new File(path + "\\javatest");
        temp.delete();
    }
}