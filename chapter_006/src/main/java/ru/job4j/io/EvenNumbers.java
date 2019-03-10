package ru.job4j.io;

import java.io.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbers {
    boolean isNumber(InputStream in) {
        int result = 1;
        char symbol;
        StringBuilder temp = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            int i;
            do {
                i = br.read();
                if (i != -1) {
                    symbol = (char) i;
                    temp.append(symbol);
                }
            } while (i != -1);
            result = Integer.valueOf(temp.toString());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return result % 2 == 0;
    }
}

