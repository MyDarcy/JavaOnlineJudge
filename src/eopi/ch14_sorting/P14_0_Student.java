package eopi.ch14_sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-29 下午2:56.
 * Description:
 */
public class P14_0_Student {
  public static class Student implements Comparable<Student> {

    private String name;
    private double gradeAverage;

    public Student(String name, double gradeAverage) {
      this.name = name;
      this.gradeAverage = gradeAverage;
    }

    @Override
    public String toString() {
      return "Student{" +
          "name='" + name + '\'' +
          ", gradeAverage=" + gradeAverage +
          '}';
    }

    @Override
    public int compareTo(Student o) {
      return name.compareTo(o.name);
    }

    public static void sortedByGPA(List<Student> students) {
      // 逆序排序即可.
      Collections.sort(students, (s1, s2) -> Double.compare(s2.gradeAverage, s1.gradeAverage));
    }
  }

  public static void main(String[] args) {
    Student s1 = new Student("he1", 90);
    Student s2 = new Student("he2", 30);
    Student s3 = new Student("he3", 20);
    Student s4 = new Student("he4", 100);
    Student s5 = new Student("he5", 80);

    List<Student> list = Arrays.asList(s1, s2, s3, s4, s5);

    Student.sortedByGPA(list);
    System.out.println(list);
  }
}
