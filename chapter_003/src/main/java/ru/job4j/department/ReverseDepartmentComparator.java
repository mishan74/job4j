package ru.job4j.department;



import java.util.Comparator;

public class ReverseDepartmentComparator implements Comparator<Department> {

    @Override
    public int compare(Department first, Department second) {
        int result = 0;
        String reg = "\\\\";
        String[] firstArr = first.getName().split(reg);
        String[] secondArr = second.getName().split(reg);
        int max = Math.max(firstArr.length, secondArr.length);
        for (int i = 0; i < max; i++) {
            if (firstArr.length - 1 < i || secondArr.length - 1 < i || result != 0) {
                break;
            }
            result = secondArr[i].compareTo(firstArr[i]);
        }
        return result;
    }
}
