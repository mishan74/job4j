package ru.job4j.socket;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Общий класс действий для клиента.
 */
public class ClientActions {
    private final Socket fileSocket;

    public ClientActions(Socket fileSocket) {
        this.fileSocket = fileSocket;
        fillActions();
    }

    /**
     * Ключ - описание действия.
     * Значение - класс действия.
     */
    private final Map<String, SimpleAction> actions = new HashMap();

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
     * Класс загрузки файла для клиента.
     */
    private class Download implements SimpleAction {
        @Override
        public void execute(String fileName) throws IOException {
            System.out.println("Укажите путь, и имя файла куда надо скачать файл");
            String file = new Scanner(System.in).nextLine();
            new SimpleLoading().load(file, fileSocket, false);
        }
    }

    /**
     * Класс выгрузки файла клиента.
     */
    private class Upload implements SimpleAction {
        @Override
        public void execute(String fileName) throws IOException {
            System.out.println("Укажите путь и имя файла, который надо загрузить");
            String file = new Scanner(System.in).nextLine();
            new SimpleLoading().load(file, fileSocket, true);
        }
    }
}


