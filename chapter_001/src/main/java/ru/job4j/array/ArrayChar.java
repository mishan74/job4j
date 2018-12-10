package ru.job4j.array;

/**
 *Class ArrayChar обертка над строкой.
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class ArrayChar {
    private final char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = false;
        char[] value = prefix.toCharArray();
        if (data.length >= value.length) {
            for (int i = 0; i < value.length; i++) {
                result = data[i] == value[i];
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }
}