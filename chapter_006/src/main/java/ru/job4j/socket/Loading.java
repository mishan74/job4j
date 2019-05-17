package ru.job4j.socket;

import java.io.IOException;
import java.net.Socket;

public interface Loading {
    /**
     * Метод для загрузки или выгрузки файла.
     * @param fileName полное имя файла.
     * @param socket сокет.
     * @param uploading если поле принимает значение true, значит происходит выгрузка файла,
     *                 если false, значит загрузка
     * @throws IOException ошибка ввода вывода.
     */
    void load(String fileName, Socket socket, boolean uploading) throws IOException;

}
