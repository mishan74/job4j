package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StudentTest {
    @Test
    public void whenAddStudentThenSorted() {
        Student student1 = new Student("Ivanov Ivan Petrovich", 2);
        Student student2 = new Student("Verzakov Sergey Gennadievich", 3);
        Student student3 = new Student("Barinov Victor Sergeevich", 4);
        Student student4 = new Student("Nagiev Dmitry Arkadievich", 5);

        List<Student> students = List.of(
                student4, student2, student3, student1
        );
        List<Student> expected = Student.levelOf(students, 4);

        List<Student> result = Arrays.asList(student1, student2);
        assertThat(result, is(expected));
    }
    @Test
    public void whenAddNullThenSorted() {
        Student student1 = new Student("Ivanov Ivan Petrovich", 2);
        Student student2 = new Student("Verzakov Sergey Gennadievich", 3);
        Student student3 = new Student("Barinov Victor Sergeevich", 4);
        Student student4 = new Student("Nagiev Dmitry Arkadievich", 5);

        List<Student> students = Arrays.asList(null, student4, student2, null, student3, student1, null);
        List<Student> expected = Student.levelOf(students, 5);

        List<Student> result = Arrays.asList(student1, student2, student3);
        assertThat(result, is(expected));
    }
}
