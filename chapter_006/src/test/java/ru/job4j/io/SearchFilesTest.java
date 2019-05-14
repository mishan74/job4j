package ru.job4j.io;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SearchFilesTest {
    private static String path;
    private final static String SP = File.separator;

    @Before
    public void init() {
        path = System.getProperty("java.io.tmpdir");

        File temp1 = new File(path + SP + "javaSearchFileTest");
        File temp2 = new File(path + SP + "javaSearchFileTest" + SP + "Under");
        boolean dir1 = temp1.mkdir();
        boolean dir2 = temp2.mkdir();
        temp1.deleteOnExit();
        temp2.deleteOnExit();
        try (
                FileOutputStream one = new FileOutputStream(path + SP + "javaSearchFileTest" + SP + "Under" + SP + "one.txt")
        ) {
            one.write("Text".getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenSearchThenLog() {
        SearchFiles searchFiles = new SearchFiles(
                path + SP + "javaSearchFileTest",
                path + SP + "javaSearchFileTest" + SP + "Log.txt",
                "e.txt",
                "-m");
        searchFiles.find();
        StringBuilder stringBuilder = new StringBuilder();
       try (BufferedReader  br = new BufferedReader(
               new FileReader(path + SP + "javaSearchFileTest" + SP + "Log.txt"))) {
           stringBuilder.append(br.readLine());
       } catch (IOException e) {
           e.printStackTrace();
       }
       File expectedFile = new File(path + SP + "javaSearchFileTest" + SP + "Under" + SP + "one.txt");
       String exp = expectedFile.getAbsolutePath();
       assertThat(stringBuilder.toString(), is(exp));
    }
    @AfterClass
    public static void deInit() {

        new File(path + SP + "javaSearchFileTest" + SP + "Log.txt").delete();
    }
}
