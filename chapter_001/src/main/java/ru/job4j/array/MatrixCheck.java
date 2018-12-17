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
        for (int i = 0; i < data.length - 1; i++) {
            int j = i + 1;
            boolean fl = data[i][i] == data [j][j];
            boolean sl = data[data.length - 1 - i][i] == data[data.length - 1 - j][j];
           result = fl == sl;
           if (!result) {
               break;
           }
        }
        return result;
    }
}