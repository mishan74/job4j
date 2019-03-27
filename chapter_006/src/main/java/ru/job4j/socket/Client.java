package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Client {
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner console = new Scanner(System.in);
            String message = "";
            String tmp;
            String query;
            do {
                query = console.nextLine();
                out.println(query);
                tmp = in.readLine();
                while (!tmp.isEmpty()) {
                    System.out.println(tmp);
                    message = tmp;
                    tmp = in.readLine();
                }
            } while (!message.equals("bye"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket(InetAddress.getByName("127.0.0.1"), 1234));
        client.start();
    }
}
