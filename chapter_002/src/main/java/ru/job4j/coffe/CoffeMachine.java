package ru.job4j.coffe;

public class CoffeMachine {
    final private int[] coins = new int[]{10, 5, 2, 1};

    /**
     * Метод подсчета сдачи в монетах.
     * @param value внесеная сумма.
     * @param price цена.
     * @return массив монет со сдачей.
     */
    int[] changes(int value, int price) throws SmallerSumException {
        int[] temp = new int[coins.length];
        int[] result = {0};

        value -= price;
        if (value < 0) {
            throw new SmallerSumException("Недостаточно средств");
        }
        if (value > 0) {
            int coin = 0;
            for (int i = 0; i < temp.length; i++) {
                temp[i] = value / coins[i];
                value -= coins[i] * temp[i];
                coin += temp[i];
            }
            result = new int[coin];
            for (int i = 0; i < temp.length; i++) {
                int count = temp[i];
                for (int j = 0; j < result.length; j++) {
                    if (count == 0 || result[j] != 0) {
                        continue;
                    }
                    result[j] = coins[i];
                    count--;
                }
            }
        }
        return result;
    }
}
