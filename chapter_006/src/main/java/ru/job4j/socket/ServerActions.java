package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Общий класс действий для клиента.
 */
public class ServerActions {
    private final ServerSocket serverSocket;
    private final BufferedReader input;
    private final PrintWriter output;

    /**
     * Ключ - описание действия.
     * Значение - класс действия.
     */
    private final Map<String, SimpleAction> actions = new HashMap();

    public ServerActions(ServerSocket serverSocket, BufferedReader input, PrintWriter output) {
        this.serverSocket = serverSocket;
        this.input = input;
        this.output = output;
        fillActions();
    }

    /**
     * Метод возвращает объект действия по ключу.
     * @param key ключ действия.
     * @return объект действия.
     */
    public SimpleAction get(String key) {
        return actions.get(key);
    }

    /**
     * Метод заполенния отоброжения классами действий.
     */
    private void fillActions() {
        actions.put("Download", new Download());
        actions.put("Upload", new Upload());
    }

    /**
     * Класс загрузки файла для сервера.
     */
    private class Download implements SimpleAction {

        @Override
        public void execute(String file) throws IOException {
            output.println("Введите имя файла" + System.lineSeparator());
            String fileName = file + File.separator + input.readLine();
            output.println("Ожидание файлового потока" + System.lineSeparator());
            try (Socket fileSocket = serverSocket.accept()) {
                new SimpleLoading().load(fileName, fileSocket, false);
            }
            output.println("Копирование файловуспешно завершено");
        }
    }

    /**
     * Клас выгрузки файла с сервера.
     */
    private class Upload implements SimpleAction {
        @Override
        public void execute(String fileName) throws IOException {
            output.println(
                    "Выбран файл "
                            + fileName
                            + ". Чтобы скачать файл введите 'yes', или любой символ для отметы "
                            + System.lineSeparator()
            );
            String answer = input.readLine();
            if (answer.equals("yes")) {
                output.println("Загрузка файла" + System.lineSeparator());
                try (Socket socket = serverSocket.accept()) {
                    new SimpleLoading().load(fileName, socket, true);
                }
                output.println("Загрузка завершена");
            }
        }
    }
}
