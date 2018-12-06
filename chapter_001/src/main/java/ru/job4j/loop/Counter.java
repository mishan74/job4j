package ru.job4j.loop;

/**
 *Class Counter счетчик.
 *@author Mikhail Rozdin
 *@since 06.12.2018
 */
public class Counter {

    /**
     * Метод суммирующий четные числа в заданном диапазоне
     * @param start начало диапазона
     * @param finish конец диапазона
     * @return сумма четных чисел диапазона.
     */
    public int add(int start, int finish) {
        int temp = 0;
        if (finish - start > 2) {
            for (int i = start; i <= finish; i++) {
                if (i % 2 == 0) {
                    temp += i;
                }
            }
        }
        return temp;
    }
}
