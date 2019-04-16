package ru.job4j.socket;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
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
public class FileManagerClientTest {
    private static final String PATH = System.getProperty("java.io.tmpdir");
    private static final String SP = File.separator;
    private static final String LS = System.lineSeparator();

    @Before
    public void  init() {
        File temp1 = new File(PATH + SP + "child");
        boolean dir1 = temp1.mkdir();
        temp1.deleteOnExit();
        File temp2 = new File(PATH + SP + "child" + SP +  "underChild");
        boolean dir2 = temp2.mkdir();
        temp2.deleteOnExit();
        File temp3 = new File(PATH + SP + "child" + SP + "two.txt");
        try  {
            boolean file1 = temp3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenHelloThenResp() throws IOException {
        Socket mainSocket = mock(Socket.class);
        System.setIn(new ByteArrayInputStream("Hello".getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("Hello" + LS + LS + "bye" + LS + LS)
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        Socket fileSocket = mock(Socket.class);
        FileManagerClient client = new FileManagerClient(mainSocket, fileSocket);

        client.start();
        assertThat(out.toString().replaceAll(LS, ""), is("Hello"));
    }
    @Test
    public void whenDownloadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        System.setIn(new ByteArrayInputStream((
                PATH + SP + "child" + SP +  "underChild" + SP + "NewFile.txt")
                .getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("Загрузка файла" + LS + LS + "bye" + LS + LS)
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        FileInputStream fileInputStream = new FileInputStream(PATH + SP + "child" + SP + "two.txt");
        Socket fileSocket = mock(Socket.class);
        when(fileSocket.getInputStream()).thenReturn(fileInputStream);
        FileManagerClient client = new FileManagerClient(mainSocket, fileSocket);
        client.start();
        File file = new File(PATH + SP + "child" + SP +  "underChild");
        String[] files = file.list();
        assertThat(files[0], is("NewFile.txt"));
        File del = new File(PATH + SP + "child" + SP +  "underChild" + SP + "NewFile.txt");
        del.delete();
    }
    @Test
    public void whenUploadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        System.setIn(new ByteArrayInputStream((
                PATH + SP + "child" + SP + "two.txt")
                .getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("Ожидание файлового потока" + LS + LS + "bye" + LS + LS)
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        FileOutputStream fileOutputStream = new FileOutputStream(
                PATH + SP + "child" + SP +  "underChild" + SP + "three.txt");
        Socket fileSocket = mock(Socket.class);
        when(fileSocket.getOutputStream()).thenReturn(fileOutputStream);


        FileManagerClient client = new FileManagerClient(mainSocket, fileSocket);

        client.start();
        File file = new File(PATH + SP + "child" + SP +  "underChild");
        String[] files = file.list();
        assertThat(files[0], is("three.txt"));
        File del = new File(PATH + SP + "child" + SP +  "underChild" + SP + "three.txt");
        del.delete();
    }
    @AfterClass
    public static void deInit() {
        new File(PATH + SP + "child" + SP + "two.txt").deleteOnExit();
    }
}
