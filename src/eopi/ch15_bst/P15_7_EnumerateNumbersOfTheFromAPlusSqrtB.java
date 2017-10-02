package eopi.ch15_bst;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Author by darcy
 * Date on 17-10-2 下午2:58.
 * Description:
 * <p>
 * 形如 a + b * sqrt(2)的k个最小值.
 * <p>
 * Design an algorithm for efficiently computing the k smallest numbers of the form
 * a + b*Math.sqrt(2) for nonnegative integers a and b.
 * <p>
 * Hint: Systematically enumerate points.
 */
public class P15_7_EnumerateNumbersOfTheFromAPlusSqrtB {

  public static class ABSqrt2 implements Comparable<ABSqrt2> {
    public int a, b;
    public double value;

    public ABSqrt2(int a, int b) {
      this.a = a;
      this.b = b;

      value = a + b * Math.sqrt(2);
    }

    @Override
    public int compareTo(ABSqrt2 o) {
      return Double.compare(value, o.value);
    }

    @Override
    public String toString() {
      return "ABSqrt2{" +
          "a=" + a +
          ", b=" + b +
          ", value=" + value +
          '}';
    }
  }

  /**
   * 找topK的问题. 利用treeSet. 同时记录index, 下一次获取值的位置(本题直接当前a+1, 或者b+1)
   * <p>
   * In each iteration we perform a deletion and two insertions. There are k such insertions,
   * so the time complexity is O(klogk). The space complexity is O(k),since there are not
   * more than 2k insertions.
   *
   * @param k
   * @return
   */
  public static List<ABSqrt2> solution(int k) {
    SortedSet<ABSqrt2> sortedSet = new TreeSet<>();
    sortedSet.add(new ABSqrt2(0, 0));

    List<ABSqrt2> result = new ArrayList<>();
    while (result.size() < k) {
      ABSqrt2 item = sortedSet.first();
      result.add(item);

      sortedSet.add(new ABSqrt2(item.a + 1, item.b));
      sortedSet.add(new ABSqrt2(item.a, item.b + 1));
      sortedSet.remove(item);
    }

    return result;
  }

  /**
   * +1, +sqrt(2)的值决定了下一个较小的值.
   * <p>
   * The idea is that the (n + l)th value will be
   * the sum of 1 or sqrt(2) with a previous value. We could iterate through all the entries in
   * the result and track the smallest such value which is greater than nth value. However,
   * this takes time O(n) to compute the (n + 1)th element.
   * <p>
   * <p>
   * Then we need to track
   * just two entries—i, the smallest index such that A[i] + 1 > A[n - 1], and j, smallest
   * index such that A[j]+ sqrt(2) > A[n-1]. Clearly, the (n + l)th entry will be the smaller of
   * A[i] +1 and A[j] + sqrt(2). After obtaining the (n+1)th entry, if it is A[i] + 1, we increment
   * i. If it is A[j] + sqrt(2); we increment j. If A[i] + 1 equals A[j] + V2, we increment both i
   * and j.
   * <p>
   * To illustrate, suppose A is initialized to (0), and i and j are 0. The computation
   * proceeds as follows:
   * 1. Since A[0] +1 = 1 < A[0] + sqrt(2) = 1.414, we push 1 into A and increment i. Now
   * A = <0,1>, i = 1,j = 0.
   * 2. Since A[1] + 1 = 2 > A[0] + sqrt(2) = 1.414, we push 1.414 into A and increment j.
   * Now A = (0,1,1.414), i=1,j=1.
   * 3. Since A[1] +1 = 2 < A[1] + sqrt(2) = 2.414, we push 2 into A and increment i. Now
   * A = <0,1,1.414, 2), i=2,j=1.
   * 4. Since A[2] +1 = 2.414 = A[1] + sqrt(2) = 2.414, we push 2.414 into A and increment
   * both i and j. Now A = (0,1,1.414, 2, 2.414), i = 3,; = 2.
   * 5. Since A[3] + 1 = 3 > A[2] + sqrt(2) = 2.828, we push 2.828 into A and increment j.
   * Now A = <0,1,1.414, 2, 2.828>, i = 3, j = 3.
   * 6. Since A[3] +1 = 3 < A[3] + V2 = 3.414, we push 3 into A and increment i. Now
   * A = <0,1,1.414, 2, 2.828,3>, i = 4, j = 3.
   *
   * @param k
   * @return
   */
  public static List<ABSqrt2> solution2(int k) {
    List<ABSqrt2> result = new ArrayList<>();
    result.add(new ABSqrt2(0, 0));
    // 要在第i个item的基础上 a+1; (总值+1)
    // 要在第j个item的基础上 b+1; (总值+sqrt(2))
    int i = 0, j = 0;

    for (int n = 0; n < k; n++) {
      ABSqrt2 first = new ABSqrt2(result.get(i).a + 1, result.get(i).b);
      ABSqrt2 second = new ABSqrt2(result.get(j).a, result.get(j).b + 1);

      // 加入较小的值.
      result.add(first.value < second.value ? first : second);

      // 更新i
      if (first.compareTo(result.get(result.size() - 1)) == 0) {
        i++;
      }

      // 更新j
      if (second.compareTo(result.get(result.size() - 1)) == 0) {
        j++;
      }
    }
    System.out.println(result.get(result.size() - 1));
    return result;
  }

  public static void main(String[] args) {
    List<ABSqrt2> result = solution(10);
    for (ABSqrt2 item : result) {
      System.out.println(item.value);
    }
    System.out.println();

    List<ABSqrt2> result2 = solution2(10);
    for (ABSqrt2 item : result2) {
      System.out.println(item.value);
    }
  }
}
