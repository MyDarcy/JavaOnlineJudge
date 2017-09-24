package eopi.ch11_heap;

import com.sun.org.apache.bcel.internal.generic.INEG;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-9-24 下午5:17.
 * Description:
 * <p>
 * 计算maxHeap中的k个最大的值.不能修改堆.
 * <p>
 * Given a max-heap, represented asan array A,design an algorithm that computes the k
 * largest elementsstored in the max-heap. You cannot modify the heap. For example, if
 * the heap is the one shown in Figure11.1(a) on Page175, then the array representation
 * is (561,314,401, 28,156,359, 271,11,3), the four largest elements are 561,314, 401,359
 */
public class P11_6_ComputeKLargestInMaxHeap {

  public static class Entry implements Comparable<Entry> {
    int index;
    int value;

    public Entry(int index, int value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public int compareTo(Entry o) {
      return Integer.compare(value, o.value);
    }
  }

  /**
   * 堆的性质.
   * 父节点比左右孩子节点的值大.
   * 封装父节点的值和index, 这种方式在前面的例题中也有看到.
   * 时间复杂度O(kLogk, 空间复杂度O(K));
   *
   * create a max-heap of candidates, initialized to hold the index 0, which
   * serves as a reference to A[0]. The indices in the max-heap are ordered according to
   * corresponding value in A. We then iteratively perform k extract-max operations from
   * the max-heap. Each extraction of an index i is followed by inserting the indices of
   * i's left child, 2i + 1, and right child, 2i + 2, to the max-heap, assuming these children
   * exist.
   * @param list
   * @param k
   * @return
   */
  public static List<Integer> kLargestInMaxHeap(List<Integer> list, int k) {
    if (k <= 0) {
      return Collections.emptyList();
    }

    // 构造一个最大堆.
    PriorityQueue<Entry> candidateMaxHeap =
        new PriorityQueue<>((e1, e2) -> Integer.compare(e2.value, e1.value));
    candidateMaxHeap.add(new Entry(0, list.get(0)));

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k; i++) {
      Integer maxElementIndex = candidateMaxHeap.peek().index;
      result.add(candidateMaxHeap.poll().value);
      int left = 2 * maxElementIndex + 1;
      if (left < list.size()) {
        candidateMaxHeap.add(new Entry(left, list.get(left)));
      }

      int right = 2 * maxElementIndex + 2;
      if (right < list.size()) {
        candidateMaxHeap.add(new Entry(right, list.get(right)));
      }
    }

    return result;

  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(561, 314, 401, 28, 156, 359, 271, 11, 3);
    List<Integer> result = kLargestInMaxHeap(list, 3);
    System.out.println(result);
  }
}
