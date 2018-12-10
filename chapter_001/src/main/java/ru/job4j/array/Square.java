package ru.job4j.array;

/**
 *Class Square возведение в квадрат всех элеметов массива
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class Square {

    /**
     * Метод возводит в квадрат каждый элемент массива, начиная с 1.
     * @param bound количество чисел от 1 до bound.
     * @return массив квадратов чисел.
     * @throws NegativeArraySizeException если число меньше 0.
     */
    public int[] calculate(int bound)throws NegativeArraySizeException {
        if (bound < 0) {
            throw new NegativeArraySizeException("Введено отрицательное число");
        }
        int[] rst = new int[bound];
        // заполнить массив через цикл элементами от 1 до bound возведенными в квадрат
        for (int i = 0, temp = 1; i < rst.length; i++, temp++) {
            rst[i] = (int) Math.pow(temp, 2);
        }
        return rst;
    }
}
