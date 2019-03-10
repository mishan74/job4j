package ru.job4j.io;

import java.io.*;
import java.util.Scanner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Abuse {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        Scanner scanner = new Scanner(in);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out)) {
            while (scanner.hasNext()) {
                String temp = scanner.nextLine();
                for (String s : abuse) {
                    temp = temp.replaceAll(s, "");
                }
                outputStreamWriter.write(temp.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
