package ru.job4j.array;

/**
 *Class Matrix multiplication table
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class Matrix {

    /**
     * Метод расчета таблицы умножения в виде матрицы.
     * @param size размерность матрицы.
     * @return двумерный массив, в форме таблицы умножения.
     * @throws NegativeArraySizeException if size < 0.
     */
    int[][] multiple(int size) {
        if (size < 0) {
            throw new NegativeArraySizeException("Не верный размер массива");
        }
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int i1 = 0; i1 < table.length; i1++) {
                table[i][i1] = (i + 1) * (i1 + 1);
            }
        }
        return table;
    }
}