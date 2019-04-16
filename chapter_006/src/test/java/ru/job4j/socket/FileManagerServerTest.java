package ru.job4j.socket;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
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
public class FileManagerServerTest {
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
        File temp3 = new File(PATH + SP + "child" + SP + "zed.txt");
        try  {
            boolean file1 = temp3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenUpToDirectoryThenContainsItFile() throws IOException {
        Socket mainSocket = mock(Socket.class);
        //System.setIn(new ByteArrayInputStream("0".getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("0" + LS + "exit")
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket fileSocket = mock(ServerSocket.class);
        FileManagerServer server = new FileManagerServer(mainSocket, fileSocket, PATH + SP + "child" + SP +  "underChild");

        server.start();
        assertThat(out.toString().replaceAll(LS, "").contains("zed.txt"), is(true));
    }
    @Test
    public void whenUploadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        //System.setIn(new ByteArrayInputStream("0".getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("2" + LS + "yes" + LS + "exit")
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket serverFileSocket = mock(ServerSocket.class);
        Socket fileSocket = mock(Socket.class);
        when(serverFileSocket.accept()).thenReturn(fileSocket);
        FileOutputStream fileOutputStream = new FileOutputStream(
                PATH + SP + "child" + SP +  "underChild" + SP + "three.txt");
        when(fileSocket.getOutputStream()).thenReturn(fileOutputStream);
        FileManagerServer server = new FileManagerServer(mainSocket, serverFileSocket, PATH + SP + "child");

        server.start();
        File file = new File(PATH + SP + "child" + SP +  "underChild");
        String[] files = file.list();
        assertThat(files[0], is("three.txt"));
        File del = new File(PATH + SP + "child" + SP +  "underChild" + SP + "three.txt");
        del.delete();
    }
    @Test
    public void whenDownloadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        //System.setIn(new ByteArrayInputStream("0".getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("1" + LS + "three.txt" + LS + "exit")
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket serverFileSocket = mock(ServerSocket.class);
        Socket fileSocket = mock(Socket.class);
        when(serverFileSocket.accept()).thenReturn(fileSocket);
        FileInputStream fileInputStream = new FileInputStream(
                PATH + SP + "child" + SP +  "zed.txt");
        when(fileSocket.getInputStream()).thenReturn(fileInputStream);
        FileManagerServer server = new FileManagerServer(mainSocket, serverFileSocket, PATH + SP + "child" + SP +  "underChild");

        server.start();
        File file = new File(PATH + SP + "child" + SP +  "underChild");
        String[] files = file.list();
        assertThat(files[0], is("three.txt"));
        File del = new File(PATH + SP + "child" + SP +  "underChild" + SP + "three.txt");
        del.delete();
    }
    @AfterClass
    public static void deInit() {
        new File(PATH + SP + "child" + SP + "zed.txt").deleteOnExit();
    }
}
