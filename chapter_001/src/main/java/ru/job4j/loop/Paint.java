package ru.job4j.loop;

/**
 * Class Paint draw pyramid
 * @author Mikhail Rozdin
 * @since 10.12.2018
 */
public class Paint {

    /**
     * Method draw pyramid
     * @param height height pyramid
     * @return pseudo graphics pyramid string.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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
