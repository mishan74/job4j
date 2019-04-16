package ru.job4j.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class SimpleLoading implements Loading {
    @Override

    public void load(String fileName, Socket socket, boolean upload) throws IOException {
        try (BufferedInputStream bin =
                     upload
                             ? new BufferedInputStream(new FileInputStream(fileName))
                             : new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream bout =
                     upload
                             ? new BufferedOutputStream(socket.getOutputStream())
                             : new BufferedOutputStream(new FileOutputStream(fileName))) {
            int b;
            while ((b = bin.read()) > 0) {
                bout.write(b);
            }
        }
    }
}
