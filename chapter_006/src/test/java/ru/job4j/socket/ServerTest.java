package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {
    private static final String LS = System.lineSeparator();

    private void serverTest(String testing, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(testing.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }
    @Test
    public void whenHelloThenGreeting() throws IOException {
        serverTest(String.join(LS, "hello", "exit"),
                String.join(LS + LS,
                "Hello, dear friend, I'm a oracle.",
                        "bye",
                        "")
        );
    }

    @Test
    public void whenUnknownPhraseThenUnderstandingAnswer() throws IOException {
        serverTest(String.join(LS, "who are you", "exit"),
                String.join(LS + LS,
                        "I don't understand you",
                        "bye",
                        "")
        );
    }

    @Test
    public void whenJustExitThenBye() throws IOException {
        serverTest(String.join(LS, "exit"),
                String.join(LS + LS,
                        "bye",
                        "")
        );
    }
}
