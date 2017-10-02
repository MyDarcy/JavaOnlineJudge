package eopi.ch15_bst;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-10-2 下午1:35.
 * Description:
 * <p>
 * 设计一个算法, 从三个已序的数组中各选一个元素使得得到的结果区间最小.
 * 即从sortedArrays中各找一个数最相近.
 * <p>
 * Design an algorithm that takes three sorted arrays and returns one entry from each
 * such that the minimum interval containing these three entries is as small as possible.
 * For example, if the three arrays are (5,10,15), (3,6,9,12,15), and (8,16,24), then
 * 15,15,16 lie in the smallest possible interval.
 * <p>
 * Hint: How would you proceed if you needed to pick three entries in a single sorted array?
 */
public class P15_6_FindTheClosestInThreeSortedArrays {

  public static class ArrayEntry implements Comparable<ArrayEntry> {
    public int value;
    public int index;

    public ArrayEntry(int value, int index) {
      this.value = value;
      this.index = index;
    }


    @Override
    public int compareTo(ArrayEntry o) {
      int result = Integer.compare(value, o.value);
      if (result == 0) {
        result = Integer.compare(index, o.index);
      }

      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof ArrayEntry)) {
        return false;
      }
      if (this == obj) {
        return true;
      }

      ArrayEntry entry = (ArrayEntry) obj;
      return this.value == entry.value && this.index == entry.index;
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, index);
    }

    @Override
    public String toString() {
      return "ArrayEntry{" +
          "value=" + value +
          ", index=" + index +
          '}';
    }
  }

  /**
   * 从三个排序的数组中各选择一个元素使得max-min的值最小.
   * 利用堆获取max和min; 然后从min关联的数组中取出下一个元素放入到堆中, 一旦min关联的数组遍历完毕,
   * 输出堆,整个过程结束.'
   * <p>
   * 本方法实现的是一个通用的解法: finds the closest entries in k sorted arrays.
   * <p>
   * For example, we begin with (5,3,8). The smallest interval whose left endpoint is
   * 3 has length8-3 = 5. The element after 3 is 6, so we continue with the triple (5,6,8).
   * The smallest interval whose left endpoint is 5 has length 8-5 = 3. The element
   * after 5 is 10, so we continue with the triple (10,6,8). The smallest interval whose left
   * endpoint is 6 has length 10- 6 = 4. The element after 6 is 9, so we continue with
   * the triple (10,9,8). Proceeding in this way, we obtain the triples (10,9,16), (10,12,16),
   * (15,12,16), (15,15,16). Out of all these triples, the one contained in a minimum length
   * interval is (15,15,16).
   * <p>
   * <p>
   * The time complexity is O(nlogk), where n is the total number of elements in the
   * k arrays. For the special case k = 3 specified in the problem statement, the time
   * complexity is O(nlog3) = O(n)
   *
   * @param sortedArrays
   * @return
   */
  public static int solution(List<List<Integer>> sortedArrays) {
    List<Integer> heads = new ArrayList<>(sortedArrays.size());

    // 现在处理到各个子数组的index.
    for (List<Integer> array : sortedArrays) {
      heads.add(0);
    }

    Integer result = Integer.MAX_VALUE;

    TreeSet<ArrayEntry> treeSet = new TreeSet<>();

    // 上面初始化的index=0的数据放入到treeSet中.
    for (int i = 0; i < sortedArrays.size(); i++) {
      treeSet.add(new ArrayEntry(sortedArrays.get(i).get(heads.get(i)), i));
    }

    while (true) {
      // treeSet中的max() - min();
      result = Math.min(result, treeSet.last().value - treeSet.first().value);

      // 较小值的index.
      int minValueIndex = treeSet.first().index;
      // 下一次该array中待处理的index.
      heads.set(minValueIndex, heads.get(minValueIndex) + 1);

      if (heads.get(minValueIndex) >= sortedArrays.get(minValueIndex).size()) {
        System.out.println(treeSet);
        return result;
      }

      // 最小值出. 从该最小值锁关联的数组中取出下一个元素放入到treeset中.
      treeSet.pollFirst();
      treeSet.add(new ArrayEntry(sortedArrays.get(minValueIndex).get(heads.get(minValueIndex)),
          minValueIndex));

    }
  }

  public static void main(String[] args) {
    List<Integer> l1 = Arrays.asList(5, 10, 15);
    List<Integer> l2 = Arrays.asList(3, 6, 9, 12, 15);
    List<Integer> l3 = Arrays.asList(8, 16, 24);
    List<List<Integer>> lists = Arrays.asList(l1, l2, l3);
    int result = solution(lists);
    System.out.println(result);
  }
}
