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
public class ClientTest {
    private static final String LS = System.lineSeparator();

    private void clientTest(String testing) throws IOException {
        Socket socket = mock(Socket.class);
        System.setIn(new ByteArrayInputStream(testing.getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(testing.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.start();
        assertThat(testing.replaceFirst(LS, ""), is(out.toString()));
    }

    @Test
    public void byeTest() throws IOException {
        clientTest(String.join(LS, "bye", "", ""));
    }
}
