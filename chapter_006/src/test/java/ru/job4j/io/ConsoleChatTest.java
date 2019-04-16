package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ConsoleChatTest {

    ConsoleChat consoleChat;
    @Before
    public void init() {

        String query = "Hello"
                + System.lineSeparator()
                + "Tell me anything"
                + System.lineSeparator()
                + "Pause"
                + System.lineSeparator()
                + "Are you hear me?"
                + System.lineSeparator()
                + "Continue"
                + System.lineSeparator()
                + "Stop";
        byte[] bytes = query.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        System.setIn(is);
        consoleChat = new ConsoleChat("text.txt",
                System.getProperty("java.io.tmpdir") + File.separator + "Log.txt");
    }

    @Test
    public void whenRunConsoleThen9LinesInLog() {
        consoleChat.start();

        try (RandomAccessFile file = new RandomAccessFile(System.getProperty("java.io.tmpdir") + File.separator + "Log.txt", "r")) {
            int count = 0;
            while (file.readLine() != null) {
                count++;
            }
            assertThat(count, is(9));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
