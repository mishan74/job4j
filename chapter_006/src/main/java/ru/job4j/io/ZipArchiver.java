package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Класс для архивации каталога, со всеми включаемыми в него файлами
 * и исключаемыми, если указаны в переменной exclude.
 */
public class ZipArchiver {
    private final File path;
    private final String output;
    private final String exclude;

    /**
     *
     * @param directory Дирректория, которую надо архивировать
     * @param exclude Файл, или расширение, которое необходимо исключить из архива
     * @param output Путь + Имя архива, или Имя (архив создастя в корне программы)
     */
    public ZipArchiver(String directory, String exclude, String output) {
        this.path = new File(directory);
        this.output = output;
        this.exclude = exclude;
    }

    /**
     * Проход по иерархии файловой структуры, проходит методом в ширину,
     * данные добавляются в Queue, достаются поочередно, и сразу проверяются не является ли файл дирректорией
     * Если файл дирректория, он добавляется в результирующую структуру Deque, также туда добавляются все элементы из этой дирректории.
     * Если файл не дирректория, осуществляется проверка на НЕсоответствие параметру exclude, если проверка прошла успешна,
     * файл добавляется в результирующую переменную Deque
     * @return Структура файлов в связном списке, без файлов exclude.
     */
    public Deque<File> seek() {
            Queue<File> temp = new LinkedList<>();
            Deque<File> result = new LinkedList<>();
            temp.offer(path);
            while (!temp.isEmpty()) {
                File file = temp.poll();
                if (file.isDirectory()) {
                    result.offer(file);
                    for (File f : file.listFiles()) {
                        temp.offer(f);
                    }
                } else {
                    if (!file.getName().contains(exclude)) {
                        result.offer(file);
                    }
                }
            }
            return result;
    }

    /**
     * Получая в параметрах структуру файлов, метод проходится по ней, извлекая элементы сконца,
     * архивируя каждый элемент. Если элемент является дирректорией, добавляется новый элемент ZipEntry,
     * если элемент не дирректория, добавляется новый элемент ZipEntry, записывается через FileInputStream
     * @param data Связный список файловой структуры.
     */
    public void zip(Deque<File> data) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(output))) {
            while (!data.isEmpty()) {
                File file = data.pollLast();
                if (file.isDirectory()) {
                    zout.putNextEntry(new ZipEntry(getPath(file) + File.separator));
                } else {
                    FileInputStream fin = new FileInputStream(file.getPath());
                    zout.putNextEntry(new ZipEntry(getPath(file)));
                    byte[] buffer = new byte[64 * 1024];
                    int length;
                    while ((length = fin.read(buffer)) != -1) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fin.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод парсит путь к файлу, удаляя путь до архива,
     * и стирая первый разделитель пути файла, если имя файла начинается с него
     * @param file файл.
     * @return форматированное полное имя файла.
     */
    private String getPath(File file) {
        String result = file.getAbsolutePath().replace(path.getPath(), "");
        if (result.startsWith(File.separator)) {
            result = result.replaceFirst("[" + File.separator + "/\\Q \\\\E]", "");
        }
        return result;
    }
}
