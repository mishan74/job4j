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
        File temp1 = new File(PATH + SP + "chil");
        boolean dir1 = temp1.mkdir();
        temp1.deleteOnExit();
        File temp2 = new File(PATH + SP + "chil" + SP +  "underChil");
        boolean dir2 = temp2.mkdir();
        temp2.deleteOnExit();
        File temp3 = new File(PATH + SP + "chil" + SP +  "underChil" + SP + "zed.txt");
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
                ("1" + LS + "exit")
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket fileSocket = mock(ServerSocket.class);
        FileManagerServer server = new FileManagerServer(mainSocket, fileSocket, PATH + SP + "chil");

        server.start();
        assertThat(out.toString().replaceAll(LS, "").contains("zed.txt"), is(true));
    }
    @Test
    public void whenUploadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        //System.setIn(new ByteArrayInputStream("0".getBytes()));
        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(String.join(LS, "1", "yes", "exit").getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket serverFileSocket = mock(ServerSocket.class);
        Socket fileSocket = mock(Socket.class);
        when(serverFileSocket.accept()).thenReturn(fileSocket);
        FileOutputStream fileOutputStream = new FileOutputStream(
                PATH + SP + "chil" + SP + "three.txt");
        when(fileSocket.getOutputStream()).thenReturn(fileOutputStream);
        FileManagerServer server = new FileManagerServer(mainSocket, serverFileSocket, PATH + SP + "chil" + SP +  "underChil");

        server.start();
        File file = new File(PATH + SP + "chil");
        File[] files = file.listFiles();
        String newFile = files[0].isFile() ? files[0].getName() : files[1].getName();
        assertThat(newFile, is("three.txt"));
        File del = new File(PATH + SP + "chil" + SP + "three.txt");
        del.delete();
    }
    @Test
    public void whenDownloadThenOk() throws IOException {
        Socket mainSocket = mock(Socket.class);
        //System.setIn(new ByteArrayInputStream("0".getBytes()));
        ByteArrayInputStream in = new ByteArrayInputStream(
                ("2" + LS + "three.txt" + LS + "exit")
                        .getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(mainSocket.getInputStream()).thenReturn(in);
        when(mainSocket.getOutputStream()).thenReturn(out);
        ServerSocket serverFileSocket = mock(ServerSocket.class);
        Socket fileSocket = mock(Socket.class);
        when(serverFileSocket.accept()).thenReturn(fileSocket);
        FileInputStream fileInputStream = new FileInputStream(
                PATH + SP + "chil" + SP +  "underChil" + SP + "zed.txt");
        when(fileSocket.getInputStream()).thenReturn(fileInputStream);
        FileManagerServer server = new FileManagerServer(mainSocket, serverFileSocket, PATH + SP + "chil");

        server.start();
        File file = new File(PATH + SP + "chil");
        File[] files = file.listFiles();
        String newFile = files[0].isFile() ? files[0].getName() : files[1].getName();
        assertThat(newFile, is("three.txt"));
        File del = new File(PATH + SP + "chil" + SP + "three.txt");
        del.delete();
    }
    @AfterClass
    public static void deInit() {
        new File(PATH + SP + "chil" + SP + "underChil" + SP + "zed.txt").deleteOnExit();
    }
}
