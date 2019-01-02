package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            Figure figure = this.figures[index];
            Cell[] steps = figure.way(source, dest);
            if (isFreeWay(steps) && steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = figure.copy(dest);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException("Фигура не найдена");
        }
        return rst;
    }

    private boolean isFreeWay(Cell[] way) throws OccupiedWayException {
        for (int i = 0; i < way.length; i++) {
            for (Figure figure : figures) {
                if (figure != null && figure.position().equals(way[i])) {
                    throw new OccupiedWayException("The way is occupied");
                }
            }
        }
        return true;
    }
}
