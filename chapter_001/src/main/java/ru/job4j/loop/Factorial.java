package ru.job4j.loop;

/**
 * Class Factorial рассчет факториала числа
 * @author Mikhail Rozdin
 * @since 07.12.2018
 */
public class Factorial {

    /**
     * Метод calc рассчитывает факториал.
     * @param n число, для которого считать факториал.
     * @return факториал положительного числа,
     * 1 если n = 0,
     * 0 если n < 0.
     */
    public int calc(int n) {
        int result = 1;
        if (n < 0) {
            result = 0;
        }
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
        return result;
    }
}
