package eopi.ch15_bst;

import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-2 下午8:43.
 * Description:
 * <p>
 * 根据已序数组创建高低最低的平衡树.
 */
public class P15_9_BuildMinimumHeightBSTFromSortedArray {

  /**
   * 要构造高度平衡的BST树,那么只需要从中间节点开始构造起即可.
   * O(n)的时间复杂度;
   *
   * @param sortedList
   * @return
   */
  public static BSTNode<Integer> solution(List<Integer> sortedList) {
    return solution(sortedList, 0, sortedList.size());
  }

  private static BSTNode<Integer> solution(List<Integer> sortedList, int start, int end) {
    if (start >= end) {
      return null;
    }

    int mid = start + (end - start) / 2;
    return new BSTNode<Integer>(sortedList.get(mid),
        solution(sortedList, start, mid),
        solution(sortedList, mid + 1, end));
  }

}
