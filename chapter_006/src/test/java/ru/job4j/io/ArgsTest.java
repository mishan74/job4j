package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ArgsTest {
    @Test
    public void whenAddArgumentsThenTrue() {
    Args args = new Args(new String[]{
            "-d", "SimpleDirectory",
            "-o", "output.txt",
            "-e", "exclude.rtf"
    });
    assertThat(args.getDirectory(), is("SimpleDirectory"));
    assertThat(args.getOutput(), is("output.txt"));
    assertThat(args.getExclude(), is("exclude.rtf"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddUnsupportedArgumentsThenEx() {
        Args args = new Args(new String[]{
                "-d", "SimpleDirectory",
                "-zzzzz", "output.txt",
                "-e", "exclude.rtf"
        });
    }
}
