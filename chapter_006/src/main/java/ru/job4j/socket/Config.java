package ru.job4j.socket;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class works with file-config (key=value format).
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Config {

    /**
     * key=value separator
     */
    private static final String SP = "=";

    /**
     * path to config file
     */
    private final String path;

    /**
     * constructor
     * @param path to file config
     */
    public Config(String path) {
        this.path = path;
    }

    /**
     * pair key-value of config
     */
    private final Map<String, String> settings = new LinkedHashMap<>();

    public Config load() {
        this.settings.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(
                    line -> {
                        if (line.contains(SP)) {
                            int pos = line.indexOf(SP);
                            settings.put(line.substring(0, pos), line.substring(pos + 1));
                        } else {
                            settings.put(line, "");
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Get parameter config
     * @param key parameter config
     * @return value of parameter config
     */
    public String value(String key) {
        return settings.get(key);
    }

    /**
     *  Set a new parameter config
     * @param key new parameter config
     * @param value value of new parameter
     */
    public void put(String key, String value) {
        settings.put(key, value);
    }

    /**
     * delete parameter config
     * @param key name of parameter
     */
    public void delete(String key) {
        settings.remove(key);
    }

    /**
     * Save configurations to config file.
     */
    public void save() {
        try (PrintWriter printWriter = new PrintWriter(this.path)) {
            this.settings.forEach(
                    (key, value) -> {
                        printWriter.append(key);
                        if (!value.isEmpty()) {
                            printWriter.append(SP).append(value);
                        }
                        printWriter.println();
                    }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
