package ru.job4j.io;

import java.io.*;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbers {
    boolean isNumber(InputStream in) {
        boolean result = false;
        char symbol;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            int i;
            int temp = -1;
            do {
                i = br.read();
                if (i != -1 && (i < 48 || i > 57)) {
                    throw new NumberFormatException("Введено не число");
                }
                if (i == -1) {
                    result = temp % 2 == 0;
                }
                temp = i;
            } while (i != -1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

