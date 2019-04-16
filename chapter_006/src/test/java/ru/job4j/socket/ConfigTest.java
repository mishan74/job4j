package ru.job4j.socket;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ConfigTest {

    @Test
    public void whenRemoveKey() throws IOException {
        File path = data(
                String.valueOf(System.currentTimeMillis()),
                "port=5231"
        );
        Config config = new Config(path.getAbsolutePath()).load();
        assertThat(config.value("port"), is("5231"));
        config.delete("port");
        config.save();
        config.load();
        assertThat(config.value("port"), is(nullValue()));
        path.deleteOnExit();
    }

    @Test
    public void whenPutNewValueThenCreate() throws IOException {
        File path = data(
                String.valueOf(System.currentTimeMillis()),
                "#comment"
        );
        Config config = new Config(path.getAbsolutePath()).load();
        assertThat(config.value("port"), is(nullValue()));
        config.put("port", "1111");
        config.save();
        config.load();
        assertThat(config.value("port"), is("1111"));
        path.deleteOnExit();
    }

    @Test
    public void whenReWriteValueThenNew() throws IOException {
        File path = data(
                String.valueOf(System.currentTimeMillis()),
                "email=mrozdin@yandex.ru"
        );
        Config config = new Config(path.getAbsolutePath()).load();
        assertThat(config.value("email"), is("mrozdin@yandex.ru"));
        config.put("email", "root@gmail.com");
        config.save();
        config.load();
        assertThat(config.value("email"), is("root@gmail.com"));
        path.deleteOnExit();
    }

    /**
     * File input date
     * @param file properties file
     * @param properties key=value
     * @return config file
     * @throws IOException io
     */
    private File data(String file, String... properties) throws IOException {
        File path = new File(
                System.getProperty("java.io.tmpdir")
                        + System.getProperty("file.separator")
                        + file
        );
        if (!path.createNewFile()) {
            throw new IllegalStateException(String.format("File could not create %s", path.getAbsoluteFile()));
        }
        try (PrintWriter printWriter = new PrintWriter(path)) {
            Stream.of(properties).forEach(printWriter::println);
        }
        return path;
    }
}
