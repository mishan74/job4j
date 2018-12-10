package ru.job4j.array;

/**
 *Class Check проверка элементов массива на идентичность.
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class Check {
    /**
     * Метод, проверяющий идентичность всех булевых элементов в массиве.
     * @param data массив булевого типа.
     * @return true если все элементы равны, false если хотя бы 1 элемент отличается.
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length - 1; i++) {
            result = data[i] == data[i + 1];
            if (!result) {
                break;
            }
       }
        return result;
    }
}
