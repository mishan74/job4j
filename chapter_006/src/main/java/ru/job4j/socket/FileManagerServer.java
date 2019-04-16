package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class FileManagerServer {
    /**
     * Основной сокет.
     */
    private final Socket socket;
    /**
     * СерверСокет для передачи/приема файлов.
     */
    private final ServerSocket fileSocket;
    /**
     * Класс загрузки/выгрузки файла.
     */
    private final SimpleLoading sLoad = new SimpleLoading();


    private static final String LS = System.lineSeparator();

    /**
     * Текущий путь.
     * Обновляется при переходе из дирректории в дирректорию.
     */
    private String root;
    /**
     * String - Список файлов и дирректорий в текущей дирректории.
     * Integer - Порядковый номер.
     */
    private final Map<Integer, String> files = new LinkedHashMap<>();

    public FileManagerServer(Socket mainSocket, ServerSocket fileSocket, String root) {
        this.socket = mainSocket;
        this.fileSocket = fileSocket;
        this.root = root;
    }
    /**
     * Метод сохраняет наименования файлов в отображение.
     */
    private void saveFiles() {
        File file = new File(root);
            String[] temp = file.list();
            this.files.clear();
            this.files.put(0, "Вверх..");
            for (int i = 0; i < temp.length; i++) {
                this.files.put(i + 1, temp[i]);
            }
            this.files.put(temp.length + 1, "Загрузить файл");
    }
    /**
     * Метод преобразовывает список файлов в удобочитаемое представление.
     * @return Строковое представление всех файлов.
     */
    private String showFiles() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> filesEntry : files.entrySet()) {
            sb.append(filesEntry.getKey())
                    .append(". ")
                    .append(filesEntry.getValue())
                    .append(LS);
        }
        return sb.toString();
    }

    /**
     * Метод загрузки файла.
     * @param name имя файла.
     * @throws IOException исключение ввода вывода.
     */
    private void downloadFile(String name) throws IOException {
        try (Socket socket = this.fileSocket.accept()) {
            this.sLoad.load(name, socket, false);
        }
    }

    /**
     * Метод выгрузки файла.
     * @param name имя файла.
     * @throws IOException исключение ввода вывода.
     */
    private void uploadFile(String name) throws IOException {
        try (Socket socket = this.fileSocket.accept()) {
            this.sLoad.load(name, socket, true);
        }
    }
    /**
     * Метод старта сервера.
     * Сервер работает в цикле. Ожидает команды.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask = "";
            while (!("exit".equals(ask))) {
                this.saveFiles();
                out.println(this.root);
                out.println(this.showFiles());
                ask = in.readLine();
                if (!("exit").equals(ask)) {
                    int action = Integer.valueOf(ask);
                    if (action == 0) {
                        root = root.substring(0, root.lastIndexOf(File.separator));
                        continue;
                    }
                    if (action == (files.size() - 1)) {
                        out.println("Введите имя файла" + LS);
                        String fileName = in.readLine();
                        out.println("Ожидание файлового потока" + LS);
                        downloadFile(root + File.separator + fileName);
                        out.println("Копирование файловуспешно завершено");
                        continue;
                    }
                    File file = new File(root + File.separator + files.get(action));
                    if (file.isDirectory()) {
                        root = file.getAbsolutePath();
                        continue;
                    }
                    if (!file.isDirectory()) {
                        out.println("Выбран файл " + file.getName() + ". Чтобы скачать файл введите 'yes', или любой символ для отметы " + LS);
                        String answer = in.readLine();
                        if (answer.equals("yes")) {
                            out.println("Загрузка файла" + LS);
                            uploadFile(file.getAbsolutePath());
                            out.println("Загрузка завершена");
                        }
                    }
                }
            }
            out.println("bye");
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Config config = new Config("C:\\projects\\app.properties").load();
        ServerSocket mainSocket = new ServerSocket(Integer.valueOf(config.value("port")));
        ServerSocket fileSocket = new ServerSocket(Integer.valueOf(config.value("filePort")));
        FileManagerServer fileManagerServer = new FileManagerServer(mainSocket.accept(), fileSocket, "C:\\projects\\job4j\\chapter_006");
        fileManagerServer.start();
    }
}
