package ru.job4j.max;

/**
 *Class Max Максимум из 2х чисел.
 *@author Mikhail Rozdin
 *@since 05.12.2018
 */
public class Max {

    /**
     * Метод возвращающий максимум из 2х чисел.
     * @param first первое число.
     * @param second второе число.
     * @return Наибольшее из 2х чисел.
     */
    public int max(int first, int second)  {
        int result;
        if (first > second) {
            result = first;
        } else {
            result = second;
        }
        return result;
    }

    /**
     * Метод возвращающий максимум из 3х чисел.
     * @param first,second,third 3 сравниваемых числа.
     * @return Наибольшее из 3х чисел.
     */
    public int max(int first, int second, int third) {
        int temp;
        temp = max(first, second);
        temp = max(temp, third);
        return temp;
    }
}
