package ru.job4j.array;

/**
 *Class BubbleSort сортировка целочисленного массива пузырьком.
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class BubbleSort {

    /**
     * Метод сортировки массива пузырьком
     * @param array массив int.
     * @return отсортированный массив int.
     */
    public int[] sort(int[] array) {
        if (array.length > 1) {
            boolean repeat;
            do {
                repeat = false;
                for (int i = 0; i < array.length - 1; i++) {
                    int temp = array[i];
                    if (temp > array[i + 1]) {
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        repeat = true;
                    }
                }
            } while (repeat);
        }
        return array;
    }
}
