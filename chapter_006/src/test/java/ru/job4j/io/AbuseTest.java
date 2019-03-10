package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class AbuseTest {
    @Test
    public void whenAddAbuseWordsThenDeleteIt() {
        Abuse abuse = new Abuse();
        ByteArrayInputStream in = new ByteArrayInputStream("Hello how are you".getBytes());
        String[] danger = new String[]{"Hello", "you"};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        abuse.dropAbuses(in, out, danger);
        String result = out.toString();
        assertThat(result, is("how are"));
    }
}
