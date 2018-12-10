package ru.job4j.array;

/**
 *Class Turn переворачивает массив.
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class Turn {

    /**
     * Метод, переворачивающий значения массива.
     * @param array целочисленный массив.
     * @return перевернутый целочисленный массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return  array;
    }
}