package eopi.ch12_search;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-24 下午7:21.
 * Description:
 */
public class P12_0_BinarySearch {


  /**
   * @param t
   * @param list
   * @return
   */
  public static int binarySearch(int t, List<Integer> list) {
    int L = 0;
    int R = list.size() - 1;
    while (L <= R) {

      // may overflow.
      // M = L + (R - L) / 2
      int M = (L + R) / 2;
      if (list.get(M) < t) {
        L = M + 1;
      } else if (list.get(M) > t) {
        R = M - 1;
      } else {
        return M;
      }
    }
    return -1;
  }


  public static void main(String[] args) {

  }

  public static class Student {
    public String name;
    public double gradePointAverage;

    Student(String name, double gradePointAverage) {
      this.name = name;
      this.gradePointAverage = gradePointAverage;
    }
  }

  // 按照GPA和name排序.
  private static final Comparator<Student> compGPA = new Comparator<Student>() {
    @Override
    public int compare(Student a, Student b) {
      if (a.gradePointAverage != b.gradePointAverage) {
        return Double.compare(a.gradePointAverage, b.gradePointAverage);
      }
      return a.name.compareTo(b.name);
    }
  };

  public static boolean searchStudent(List<Student> students, Student target,
                                      Comparator<Student> compGPA) {
    return Collections.binarySearch(students, target, compGPA) >= 0;
  }
}
