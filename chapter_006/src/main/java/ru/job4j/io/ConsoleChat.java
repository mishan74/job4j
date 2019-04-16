package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class ConsoleChat {
    private final File sourceFile;
    private final File log;
    private final List<String> phrases = new ArrayList<>();
    private final Scanner scanner;
    private final String bot = "BOT: ";
    private final String user = "USER: ";


    public ConsoleChat(String sourceFile, String log) {
        this.sourceFile = new File(sourceFile);
        this.log = new File(log);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Основной метод класса, запускает цикл, в котором происходит работа чата.
     */
    public void start() {
        findAllPhrases(sourceFile);
        try (RandomAccessFile rout = new RandomAccessFile(sourceFile, "r");
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log)))) {
            String userText = null;
            do {
                if (userText != null) {
                    readPhrase(bout);
                }
                System.out.print(user);
                userText = scanner.nextLine();
                bout.append(user);
                bout.append(userText);
                bout.append(System.lineSeparator());

                if (userText.equals("Pause")) {
                    takePause(bout);
                }
            }
            while (!userText.equals("Stop"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Если пользователь ввел "Стоп", то попадает в этот метод, где в цикле ожидается фраза "Продолжить"
     * @param writer Поток записи в лог.
     * @throws IOException ошибка ввода вывода
     */
    private void takePause(Writer writer) throws IOException {
        String checkText;
        do {
        System.out.print(user);
        checkText = scanner.nextLine();
        writer.append(user);
        writer.append(checkText);
        writer.append(System.lineSeparator());

        } while (!checkText.equals("Continue"));
    }

    /**
     * Метод читает рандомную фразу из файла.
     * @param writer Поток записи в лог.
     * @throws IOException ошибка ввода вывода
     */
    private void readPhrase(Writer writer) throws IOException {
        int random = (int) (Math.random() * phrases.size());
        String result = bot
                + phrases.get(random)
                + System.lineSeparator();
        System.out.print(result);
        writer.append(result);
    }

    /**
     * Метод ищет все точки в файле, затем добавляет в список предложение.
     * @param file Файл, который следует проверить
     */
    public void findAllPhrases(File file) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            if (file.length() < 1) {
                throw new IOException("Empty File");
            }
            int character;
            StringBuilder sb = new StringBuilder();
            while ((character = br.read()) != -1) {
                if (((char) character) != '.') {
                    sb.append((char) character);
                    continue;
                }
                this.phrases.add(sb.toString());
                sb.delete(0, sb.capacity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("text.txt", "log.txt");
        consoleChat.start();
    }
}