package ru.job4j.calculator;

/**
 *Class Fit программа расчета идеального веса
 *@author Mikhail Rozdin
 *@since 05.12.2018
 */
public class Fit {
    /**
     * Идеальный вес для мужщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        if (height < 100) {
            return 0;
        }
        return (height - 100) * 1.15;
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        if (height < 110) {
            return 0;
        }
        return (height - 110) * 1.15;
    }
}
