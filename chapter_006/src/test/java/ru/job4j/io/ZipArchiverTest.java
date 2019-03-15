package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ZipArchiverTest {
    String path;
    final String separator = File.separator;

    @Before
    public void init() {
        path = System.getProperty("java.io.tmpdir");

        File temp = new File(path + separator + "javaZipTest");
        boolean dir1 = temp.mkdir();
        try (
                FileOutputStream one = new FileOutputStream(path + separator + "javaZipTest" + separator + "one.txt")
        ) {
            one.write("Text".getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddZipThenArchivIsCreated() {
        ZipArchiver zipArchiver = new ZipArchiver(path + separator + "javaZipTest", ".rtf", path + separator + "testJZip.zip");
        zipArchiver.doZip();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path + separator + "testJZip.zip"))) {
            ZipEntry zEntry;
            while ((zEntry = zipInputStream.getNextEntry()) != null) {
                if (!zEntry.isDirectory()) {
                assertNotEquals((zEntry.getName()), (".rtf"));
                }
                zipInputStream.closeEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(path + separator + "testJZip.zip");
        assertThat(file.delete(), is(true));
    }
}
