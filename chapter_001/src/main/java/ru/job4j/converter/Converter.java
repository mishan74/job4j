package ru.job4j.converter;

/**
 *Class Converter Конвертор валюты.
 *@author Mikhail Rozdin
 *@since 05.12.2018
 */
public class Converter {
    /**
     * Курс рубля к евро
     */
    private final int eurRub = 70;

    /**
     * Курс рубля к доллару
     */
    private final int usdRub = 60;
    
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value / eurRub;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары.
     */
    public int rubleToDollar(int value) {
        return value / usdRub;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Рубли.
     */
    public int euroToRuble(int value) {
        return value * eurRub;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Рубли.
     */
    public int dollarToRuble(int value) {
        return value * usdRub;
    }
}
