package ru.job4j.tictactoe;


import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean fillXO(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        var result = false;
        for (int i = 0; i != table.length; i++) {
            result = predicate.test(table[startX][startY]);
            if (!result) {
                break;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        return fillXO(predicate, 0, 0, 1, 0)
                || fillXO(predicate, 0, 1, 1, 0)
                || fillXO(predicate, 0, 2, 1, 0)
                || fillXO(predicate, 0, 0, 0, 1)
                || fillXO(predicate, 1, 0, 0, 1)
                || fillXO(predicate, 2, 0, 0, 1)
                || fillXO(predicate, 0, 0, 1, 1)
                || fillXO(predicate, 2, 0, -1, 1);
    }
    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    public boolean hasGap() {
        return Arrays
                .stream(table)
                .flatMap(Arrays::stream)
                .anyMatch(x -> !x.hasMarkO() && !x.hasMarkX());
    }
}