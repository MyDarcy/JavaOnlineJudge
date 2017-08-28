package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午3:57.
 * Description:
 * 计算给定集合S所有只含有k的元素的所有子集合.
 */
public class P6_SubSetOfK {

  /**
   * 最简单的方法遍历同上一道题目一样，遍历所有的子集合,然后size = k的放入到结果集合中.
   *
   * 时间复杂度是O(n2^n)
   *
   * @param n
   * @param k
   * @return
   */
  public static List<List<Integer>> bruteForceSolution(int n, int k) {

    return null;
  }

  /**
   *
   * @param n
   * @param k
   * @return
   */
  public static List<List<Integer>> solution(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    solution(n, k, 1, new ArrayList<Integer>(), result);
    return result;
  }

  /**
   * offset: 1...n
   *
   * To gain efficiency, we use a more focused approach. In particular, we can make
   * nice use of case analysis. There are two possibilities for a subset -- it does not contain
   * 1, or it does contain 1. In the first case, we return all subsets of size k of {2,3} in
   * the second case, we compute all k -1 sized subsets of {2,3,..., n} and add 1 (thus make size k)
   * to each of them.
   *
   * For example, if n = 4 and k = 2, then we compute all subsets of size 2 from {2,3,4),
   * and all subsets of size 1 from (2,3,4). We add 1 to each of the latter, and the result is
   * the union of the two sets of subsets, i.e., {{2,3}, {2,4},{3,4}} U {{1,2}, {1,3},{1,4}}
   *
   * @param n
   * @param k
   * @param offset
   * @param integers
   * @param result
   */
  private static void solution(int n, int k, int offset, ArrayList<Integer> integers, List<List<Integer>> result) {
    if (integers.size() == k) {
      result.add(new ArrayList<>(integers));
    } else {
      // 还需要挑选这么多个元素.
      int numberRemaning = k - integers.size();
      // 保证从offset开始, 还有至少numberRemaing个元素可供选择.

      // 在进入下一轮循环的时候,实际上栈上的参数保持不变.
      for (int i = offset; i <= n && numberRemaning <= (n - i + 1); i++) {

        // 包含元素i进入下一轮. 还需要从剩余的元素中选择k - size个元素.
        integers.add(i);
        solution(n, k, offset + 1, integers, result);
        // 不包含元素进入下一轮.
        integers.remove(integers.size() - 1);

        // 实际上外面的循环已经做了这里要做的工作.
        /*solution(n, k, offset + 1, integers, result);*/
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> result = solution(4, 2);
    System.out.println(result);
  }

}
