package ru.job4j.pseudo;

import java.util.StringJoiner;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 * Клас треугольник.
 * Реализует метод draw, возвращая псевдографику
 * в виде объекта типа String с формой треугольника.
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        StringJoiner pic = new StringJoiner(System.lineSeparator());
        pic.add("   *   ");
        pic.add("  ***  ");
        pic.add(" ***** ");
        return pic.toString();
    }
}
