package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.RookBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogicTest {

    @Test
    public void whenWayIsFreeThanMoveTrue() {
       Logic logic = new Logic();
       logic.add(new BishopBlack(Cell.E2));
       boolean expected = logic.move(Cell.E2, Cell.C4);
       assertThat(expected, is(true));
    }

    @Test
    public void whenWayIsOccupiedThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.H3));
        logic.add(new RookBlack(Cell.G4));
        boolean expected = logic.move(Cell.H3, Cell.F5);
        assertThat(expected, is(false));

    }
}
