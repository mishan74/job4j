package ru.job4j.coffe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeMachineTest {

    @Test
    public void whenValueGiveMoneyThenSeventeenArray() {
        CoffeMachine coffeMachine = new CoffeMachine();
        int[] res = coffeMachine.changes(100, 83);
        int[] array = new int[]{10, 5, 2};
        assertThat(res, is(array));
    }

    @Test
    public void whenValueGiveMoneyThenOneCoin() {
        CoffeMachine coffeMachine = new CoffeMachine();
        int[] res = coffeMachine.changes(100, 99);
        int[] array = new int[]{1};
        assertThat(res, is(array));
    }
    @Test
    public void whenValueIsMoneyThenZero() {
        CoffeMachine coffeMachine = new CoffeMachine();
        int[] res = coffeMachine.changes(100, 100);
        int[] array = new int[]{0};
        assertThat(res, is(array));
    }

    @Test (expected = SmallerSumException.class)
    public void whenValueMorePriceThenException() {
        CoffeMachine coffeMachine = new CoffeMachine();
        coffeMachine.changes(10, 100);
    }
}
