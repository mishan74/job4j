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
}
