package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isDiagonale(source, dest)) {
            throw new ImpossibleMoveException("The Bishop can move for diagonal only.");
        }
        Cell[] way = new Cell[Math.abs(source.x - dest.x)];
        for (int i = 0; i < Math.abs(source.x - dest.x); i++) {
            int j = i + 1;
            int x = source.x < dest.x ? source.x + j : source.x - j;
            int y = source.y < dest.y ? source.y + j : source.y - j;
            Cell temp = findCell(x, y);
            way[i] = temp;
        }
        return way;
    }

    public Cell findCell(int x, int y) {
        Cell res = null;
        for (Cell temp : Cell.values()) {
            if (temp.x == x && temp.y == y) {
              res = temp;
              break;
          }
      }
      return res;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    private boolean isDiagonale(Cell source, Cell dest) {
        return (Math.abs(dest.x - source.x) == (Math.abs(dest.y - source.y)));
    }
}
