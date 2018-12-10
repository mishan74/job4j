package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Class Paint draw pyramid
 * @author Mikhail Rozdin
 * @since 10.12.2018
 */
public class Paint {

    /**
     * метод прорисолвки правостороннего треугольника.
     * @param height высота треугольника.
     * @return треугольник в псевдо графике.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * метод прорисолвки левостороннего треугольника.
     * @param height высота треугольника.
     * @return треугольник в псевдо графике.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }
    /**
     * Method draw pyramid
     * @param height height pyramid
     * @return pseudo graphics pyramid string.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Method loopBy содержит главную логику построения пирамиды.
     * @param height высоат пирамиды.
     * @param weight ширина пирамиды.
     * @param predict условие проставление галки.
     * @return пирамида в псевдо графике.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
