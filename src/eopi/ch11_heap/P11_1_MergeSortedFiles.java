package eopi.ch11_heap;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-22 下午3:26.
 * Description:
 * <p>
 * 合并文件.
 * <p>
 * 排序有序的文件.
 */
public class P11_1_MergeSortedFiles {

  static class Entry {
    int value;
    int arrayIndex;

    public Entry(int value, int arrayIndex) {
      this.value = value;
      this.arrayIndex = arrayIndex;
    }
  }

  /**
   * 合并多个有序数组.
   * 最小堆中最多有k个元素.归并的时间复杂度O(NlogK);空间复杂度O(K);
   * <p>
   * 就是每个array都要保证在堆中存在一个值, 因为每个array都是顺序的,那么一定能够在这k个元素中获取最小值.
   * 而且每次从堆中获取到了最小值以后, 那么该元素所属array的下一个元素入堆,所以要记录每一个入堆的元素的arrayIndex,
   *
   *
   * 方法2: 首尾两个list归并到首部, 直到只剩下一个list为止.
   * 参见cleancode1.0.3中的实现.
   *
   * @param lists
   */
  public static List<Integer> solution(List<List<Integer>> lists) {
    List<Iterator<Integer>> iterators = new ArrayList<>(lists.size());

    // 创建iterator的list.
    for (int i = 0; i < lists.size(); i++) {
      iterators.add(lists.get(i).iterator());
    }

    PriorityQueue<Entry> priorityQueue =
        new PriorityQueue<>((e1, e2) -> Integer.compare(e1.value, e2.value));


    for (int i = 0; i < iterators.size(); i++) {
      if (iterators.get(i).hasNext()) {
        priorityQueue.add(new Entry(iterators.get(i).next(), i));
      }
    }

    List<Integer> result = new LinkedList<>();
    while (!priorityQueue.isEmpty()) {
      Entry entry = priorityQueue.poll();
      result.add(entry.value);
      if (iterators.get(entry.arrayIndex).hasNext()) {
        priorityQueue.add(new Entry(iterators.get(entry.arrayIndex).next(), entry.arrayIndex));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<List<Integer>> lists = new ArrayList<>();
    lists.add(Arrays.asList(1, 4, 7, 10));
    lists.add(Arrays.asList(2, 5, 8, 11));
    lists.add(Arrays.asList(3, 6, 9, 12, 15));
    List<Integer> result = solution(lists);
    System.out.println(result);
  }

}
