package ru.job4j.array;

/**
 *Class MatrixCheck
 *@author Mikhail Rozdin
 *@since 11.12.2018
 */
public class MatrixCheck {
    /**
     * Метод, проверяющий идентичность значений по диагоналям в двумерном массиве.
     * @param data двумерный массив.
     * @return true, если значения идентичны, false если нет.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
           result = data[i][i] == data [i][data.length - 1 - i];
           if (!result) {
               break;
           }
        }
        return result;
    }
}