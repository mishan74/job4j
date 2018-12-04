package ru.job4j.calculator;

/**
 *Class Calculator элементарный калькулятор
 *@author Mikhail Rozdin
 *@since 04.12.2018
 */
public class Calculator {
    private double result;

    /**
     * Метод сложения двух чисел.
     * @param first,second входные параметры.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод вычитания двух чисел.
     * @param first,second входные параметры.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод деления двух чисел.
     * @param first,second входные параметры.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод умножения двух чисел.
     * @param first,second входные параметры.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     * Метод умножения двух чисел.
     * @return  result результат последнего действия.
     */
    public double getResult() {
        return this.result;
    }
}
