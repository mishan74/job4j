package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Search {
    List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> data = new LinkedList<>();
        data.offer(new File(parent));
        while (!data.isEmpty()) {
            File temp = data.poll();
            if (temp.isDirectory()) {
                for (File f : temp.listFiles()) {
                    data.offer(f);
                }
            } else {
                for (String ext : exts) {
                    if (temp.getName().contains(ext)) {
                       result.add(temp);
                    }
                }
            }
        }
        return result;
    }
}
