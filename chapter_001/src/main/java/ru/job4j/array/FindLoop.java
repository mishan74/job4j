package ru.job4j.array;

/**
 *Class FindLoop
 *@author Mikhail Rozdin
 *@since 10.12.2018
 */
public class FindLoop {

    /**
     * Метод поиска индекса искомого числа.
     * @param data Массив, передаваемый для поиска.
     * @param el Элемент, который ищем в массиве.
     * @return Индекс элемента найденного числа, или -1 в случае неудачи.
     */
    public int indexOf(int[] data, int el) {
    int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
        if (data[index] == el) {
            rst = index;
            break;
        }
    }
    return rst;
}
}
