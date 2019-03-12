package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Abuse {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        List<String> check = Arrays.asList(abuse);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
               outputStreamWriter.write(
                       br.lines()
                               .flatMap(x -> Stream
                                       .of(x.split(" "))
                                       .filter(z -> !check.contains(z)))
                               .collect(Collectors.joining(" "))
                );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}