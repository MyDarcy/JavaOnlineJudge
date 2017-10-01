package eopi.ch14_sorting;

import com.sun.org.apache.bcel.internal.generic.INEG;

import javax.swing.plaf.PanelUI;
import javax.swing.text.html.parser.Entity;
import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-30 下午3:59.
 * Description:
 * <p>
 * 排序一个数组,其中存在很多的重复元素.
 * 这里的排序是只要求相同的元素相邻即可, 对严格的大小关系没有限制.
 * <p>
 * You aregiven an array of student objects. Eachstudent hasan integer-valued age field
 * that is to be treated as a key. Rearrange the elements of the array so that students of
 * equal age appear together. The order in which different ages appear is not important.
 * How would your solution change if ages have to appear in sorted order?
 * <p>
 * Hint: Count the number of students for each age.
 */
public class P14_7_PartitionAndSorintAnArrayWithManyRepeatedEntries {

  public static class Student {
    public String name;
    public int age;

    public Student(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return "Student{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }

  /**
   * 解法1: 排序.时间复杂度O(NlogN), 空间复杂度O(1).
   * 解法2: 记录以age为key的对象的个数. 然后迭代原始数组, 把不同key的对象安排到合适的位置上去. 时间复杂度O(N), 空间复杂度O(N)
   * 解法3:
   * The idea is to maintain a subarray for each of the different types of elements.
   * Each subarray marks out entries which have not yet been assigned elements of that type.
   * We swap elements across these subarrays to move them to their correct position.
   * <p>
   * In the program below, we use two hash tables to track the subarrays. One is the
   * starting offset of the subarray, the other its size. As soon as the subarray becomes
   * empty, we remove it.
   * <p>
   * <p>
   * The time complexity is O(n), since the first pass entails n hash table inserts, and
   * the second pass spends O(1) time to move one element to its proper location. The
   * additional space complexity is dictated by the hash table, i.e., O(m), where m is the
   * number of distinct ages
   * <p>
   * If the entries are additionally required to appear sorted by age, we can use a BST-based map
   * (Chapter 15) to map ages to counts, since the BST-based map keeps ages in sorted order. For
   * our example, the age-count pairs would appear in the order(11,1), (12, 2), (13,3), (14, 2).
   * The time complexity becomes O(n + mlog m), since BST insertion takes time O(log m).
   * Such a sort is often referred to as a counting sort.
   *
   * @param students
   */
  public static void solution(List<Student> students) {

    // 对年龄的计数.
    Map<Integer, Integer> ageToCount = new HashMap<>();
    for (int i = 0; i < students.size(); i++) {
      int age = students.get(i).age;
      Integer count = ageToCount.get(age);
      if (count == null) {
        count = 0;
      }
      count++;
      ageToCount.put(age, count);
    }

    // {11=1, 12=2, 13=3, 14=2}
    System.out.println(ageToCount);

    // 年龄为age的person在结果集合中的偏移量.
    int offset = 0;
    Map<Integer, Integer> ageToOffset = new HashMap<>();
    for (Map.Entry<Integer, Integer> entry : ageToCount.entrySet()) {
      Integer value = entry.getValue();
      ageToOffset.put(entry.getKey(), offset);
      offset += value;
    }

    // {11=0, 12=1, 13=3, 14=6}
    System.out.println(ageToOffset);

    // while循环实际要迭代的次数是offset次, 也就是student.size()次.
    // 因为students是不断更新的.
    while (!ageToOffset.isEmpty()) {
      // age->offset的映射关系.
      Map.Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();

      // offset不断更新后的值覆盖 0 ~ size()-1,
      int toAge = students.get(from.getValue()).age;

      // 这个对象实际要移动到的目标位置.
      Integer toValue = ageToOffset.get(toAge);

      Collections.swap(students, from.getValue(), toValue);

      Integer count = ageToCount.get(toAge) - 1;
      ageToCount.put(toAge, count);
      if (count > 0) {
        // 同age的对象下一次可以放入的位置.
        ageToOffset.put(toAge, toValue + 1);
      } else {
        ageToOffset.remove(toAge);
      }
    }
  }

  public static void main(String[] args) {
    List<Student> students = Arrays.asList(
        new Student("Greg", 14),
        new Student("John", 12),
        new Student("Andy", 11),
        new Student("Jim", 13),
        new Student("Phil", 12),
        new Student("Bob", 13),
        new Student("Chip", 13),
        new Student("Tim", 14)
    );

    solution(students);

    for (Student student : students) {
      System.out.println(student);
    }
  }

}
