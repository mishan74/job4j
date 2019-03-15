package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Класс считывает вводимые данные с коммандной строки
 * записывает их в Map action
 *
 */
public class Args {
    private final Map<String, String> action = new HashMap<>();
    private final List<String> inputArg = Arrays.asList("-d", "-e", "-o");

    public Args(String[] args) {
        parserCommand(args);
    }

    public String getDirectory() {
        return action.get("-d");
    }

    public String getExclude() {
        return action.get("-e");
    }

    public String getOutput() {
        return action.get("-o");
    }

    private void parserCommand(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) {
            if (inputArg.contains(args[i])) {
                action.put(args[i], args[i + 1]);
            }
        }
        if (action.size() != inputArg.size()) {
            throw new IllegalArgumentException("Введены неверные аргументы");
        }
    }

    /**
     * После инициализации аргументов передаем в упорядоченном виде
     * в класс ZipArchiver для архивации
     * @param args вводимые аргументы
     */
    public static void main(String[] args) {
        Args result = new Args(args);
        String directory = result.action.get("-d");
        String exclude = result.action.get("-e");
        String output = result.action.get("-o");
        ZipArchiver zipArchiver = new ZipArchiver(directory, exclude, output);
        zipArchiver.doZip();
    }
}
