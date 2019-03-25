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
    private final Scanner scanner;
    private List<Integer> points;
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
        points = this.findAllPoints(sourceFile);
        try (RandomAccessFile rout = new RandomAccessFile(sourceFile, "r");
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log)))) {
            String userText = null;
            do {
                if (userText != null) {
                    readPhrase(rout, bout);
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
     * Метод читает рандомную фразу из файла. Вначале определяется рандомная позиция,
     * затем побайтово считывается фраза, до символа '.' Метод не учитывает, что возможно чтение до конца
     * файла, тк подразумевает, что последняя фраза также заканчивается символом '.'
     * @param file Файловый поток чтения.
     * @param writer Поток записи в лог.
     * @throws IOException ошибка ввода вывода
     */
    private void readPhrase(RandomAccessFile file, Writer writer) throws IOException {
        int random = (int) (Math.random() * points.size());
        file.seek(points.get(random));
        char c;
        StringBuilder sb = new StringBuilder();
        sb.append(bot);
        while ((c = (char) file.read()) != '.') {
            sb.append(c);
        }
        sb.append(System.lineSeparator());
        String result = sb.toString();
        System.out.print(result);
        writer.append(result);
    }

    /**
     * Метод ищет все точки в файле, затем добавляет в список позицию следующего предложения.
     * @param file Файл, который следует проверить
     * @return Список с координатами начал предложений в файле
     */
    public List<Integer> findAllPoints(File file) {
        List<Integer> result = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(file)) {
            if (file.length() < 1) {
                throw new IOException("Empty File");
            }
            int count = 0;
            int check;
            while ((check = inputStream.read()) != -1) {
                if ((char) check == '.') {
                    result.add(count + 2);

                }
                count++;
            }
            result.set(result.size() - 1, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}