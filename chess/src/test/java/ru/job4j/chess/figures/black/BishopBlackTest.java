package ru.job4j.chess.figures.black;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BishopBlackTest {

    @Test
    public void whenDiagonalThanMove() {
        BishopBlack bb = new BishopBlack(Cell.A1);
        Cell[] move = bb.way(Cell.A1, Cell.D4);
        Cell[] expected = new Cell[] {Cell.B2, Cell.C3, Cell.D4};
        assertThat(move, is(expected));
    }

    @Test (expected = ImpossibleMoveException.class)
    public void whenNotDiagonalThanException() {
        BishopBlack bb = new BishopBlack(Cell.A1);
        bb.way(Cell.A1, Cell.E4);
    }
}
