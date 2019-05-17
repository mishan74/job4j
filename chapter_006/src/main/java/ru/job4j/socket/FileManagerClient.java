package ru.job4j.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class FileManagerClient {
    /**
     * Основной сокет.
     */
    private final Socket socket;

    /**
     * Класс действий клиента
     */
    ClientActions clientActions;
    /**
     * Файловый сокет.
     */
    private final Socket fileSocket;

    public FileManagerClient(Socket mainSocket, Socket fileSocket) {
        this.socket = mainSocket;
        this.fileSocket = fileSocket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            Scanner console = new Scanner(System.in);
            String message = "";
            String tmp;
            String query;
            clientActions = new ClientActions(fileSocket);
            do {
                tmp = in.readLine();
                while (!tmp.isEmpty()) {
                    System.out.println(tmp);
                    message = tmp;
                    tmp = in.readLine();
                }
                if (!message.equals("bye")) {
                    if (message.equals("Ожидание файлового потока")) {
                        this.clientActions.get("Upload").execute("");
                        continue;

                    }
                    if (message.equals("Загрузка файла")) {
                        this.clientActions.get("Download").execute("");
                        continue;
                    }
                    query = console.nextLine();
                    out.println(query);
                }
            } while (!message.equals("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Config config = new Config("C:\\projects\\app.properties").load();
        Socket socket =  new Socket(config.value("InetAddress"), Integer.valueOf(config.value("port")));
        Socket fileSocket =  new Socket(config.value("InetAddress"), Integer.valueOf(config.value("filePort")));
        FileManagerClient fileManagerClient = new FileManagerClient(socket, fileSocket);
        fileManagerClient.start();
    }
}
