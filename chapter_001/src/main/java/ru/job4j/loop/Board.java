package ru.job4j.loop;

/**
 * Class Board шахматная доска.
 * @author Mikhail Rozdin
 * @since 09.12.2018
 */
public class Board {

    /**
     * Method paint - draws a board
     * @param width board width
     * @param height board height
     * @return pseudo graphics board string.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int i1 = 0; i1 < width; i1++) {
                // условие проверки, что писать пробел или X
                if ((i1 + i) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }
}
