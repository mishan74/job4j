package ru.job4j.array;

/**
 * Class ArraySorted объединение сортированных массивов.
 */
public class ArraySorted {

    /**
     * Метод, объединяющий 2 сортированных массива в третий сортированный.
     * @param first первый сортированный массив.
     * @param second второй сортированный массив.
     * @return обхединенный отсортированный массив.
     */
    int[] sortedArray(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int fl = first.length - 1;
        int sl = second.length - 1;
        int rl = result.length;
        while (rl > 0) {
            if (sl < 0 || fl > 0 && first[fl] >= second[sl]) {
                result[--rl] = first[fl--];
            } else {
                result[--rl] = second[sl--];
            }
        }
        return result;
    }
}
