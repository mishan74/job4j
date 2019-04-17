package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SearchArgs {
    private final Map<String, String> action = new HashMap<>();
    private final List<String> inputArg = Arrays.asList("-d", "-n", "-o");

    public SearchArgs(String[] args) {
        parserCommand(args);
    }

    public String getDirectory() {
        return action.get("-d");
    }

    public String getName() {
        return action.get("-n");
    }

    public String getOutput() {
        return action.get("-o");
    }
    public String getParameter() {
        return action.get("parameter");
    }

    private void parserCommand(String[] args) {
        if (args.length != 7) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) {
            if (inputArg.contains(args[i])) {
                action.put(args[i], args[i + 1]);
            }
            if (args[i].contains("-m") || args[i].contains("-f") || args[i].contains("-r")) {
                action.put("parameter", args[i]);
            }
        }
        if (action.size() != inputArg.size() + 1) {
            throw new IllegalArgumentException("Введены неверные аргументы");
        }
    }
    public static void main(String[] args) {
        SearchArgs result = new SearchArgs(args);
        String directory = result.action.get("-d");
        String name = result.action.get("-n");
        String output = result.action.get("-o");
        String parameter = result.action.get("parameter");
        System.out.println(result.action);
        SearchFiles searchFiles = new SearchFiles(directory, output, name, parameter);
        searchFiles.find();
    }
}
