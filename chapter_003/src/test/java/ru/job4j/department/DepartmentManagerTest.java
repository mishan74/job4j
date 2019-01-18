package ru.job4j.department;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DepartmentManagerTest {
    @Test
    public void whenSimpleSortedThenSort() {
        DepartmentManager departmentManager = new DepartmentManager();
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
        };
        departmentManager.add(departments);
        Department[] result = departmentManager.getSort();
        Department[] expect = new Department[] {
                new Department("K1"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K1\\SK2"),
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K2\\SK1\\SSK2")
        };
        assertThat(result, is(expect));
    }
    @Test
    public void whenReverseSortedThenReverse() {
        DepartmentManager departmentManager = new DepartmentManager();
        String[] departments = {
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
        };
        departmentManager.add(departments);
        Department[] result = departmentManager.getReverseSort();
        Department[] expect = new Department[] {
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK2"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K1"),
                new Department("K1\\SK2"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K1\\SK1\\SSK1")
        };
        assertThat(result, is(expect));
    }
}
