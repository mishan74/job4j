package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SearchFiles {
    private final String directory;
    private final String log;
    private final String search;
    private final String parameter;

    public SearchFiles(String directory, String log, String search, String parameter) {
        this.log = log;
        this.directory = directory;
        this.search = search;
        this.parameter = parameter;
    }
    public void find() {
        Queue<File> data = new LinkedList<>();
        data.offer(new File(this.directory));
        while (!data.isEmpty()) {
            File temp = data.poll();
            if (temp.isDirectory()) {
                for (File f : temp.listFiles()) {
                    data.offer(f);
                }
            } else {
                switch (this.parameter) {
                    case "-m":
                        if (temp.getName().contains(this.search)) {
                            writeLog(temp);
                        }
                        break;
                    case "-r":
                        Pattern pattern = Pattern.compile(search);
                        Matcher mather = pattern.matcher(temp.getName());
                        if (mather.find()) {
                            this.writeLog(temp);
                        }
                        break;
                    case "-f":
                        if (temp.getName().equals(this.search)) {
                            writeLog(temp);
                        }
                        break;
                        default: throw new IllegalArgumentException();
                }
            }
        }
    }

    private void writeLog(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.log, true)) {
            fileOutputStream.write((file.getAbsolutePath() + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
