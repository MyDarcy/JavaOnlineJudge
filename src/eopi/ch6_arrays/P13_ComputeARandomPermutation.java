package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-17 下午2:10.
 * Description:
 *
 * 设计一个算法能改生成{0,1,...n-1}的随机均匀的permutation.所有可能的permutation都有相同的概率.
 *
 * Design an algorithm that creates uniformly random permutations of {0,1,... n-1}.You are
 * given a random number generator that returns integers in the set {0,1,...,n-1}
 * with equal probability; use as few calls to it as possible.
 *
 * Hint: If the result is stored in A,how would you proceed once A[n-1] is assigned correctly?
 *
 */
public class P13_ComputeARandomPermutation {

  /**
   * 迭代的选择[0, n-1]之间的数, 重复的话就再次选择,直到选择到了n个数字. 使用HashMap来表示number是否被选择过.
   *
   * For example, if n = 4, we might have the sequence 1, 2,1 (repeats), 3,1(repeats), 2
   * (repeat),0 (done, all numbersfrom 0 to 3 are present). The corresponding permutation
   * is (1,2,3,0).
   *
   * 空间复杂度O(n),时间复杂度O(nlogn)
   *
   *
   * @param n
   * @return
   */
  public static List<Integer> bruteForceSolution(int n) {

    return null;
  }


  /**
   * 因为暴力的方案里面存储大量的重复选择,所以最终的时间复杂度也上来了.是O(nlogn)
   *
   * 采用swap的机制. 后面randdom.nextInt(given.size() - i) + i可以生成的index的个数分别是
   * n, n-1, n-2, n-3, n-4....(i = 0,1,...n - 1);这就相当于是 n! 的各个部分.
   *
   * 时间复杂度O(n),空间复杂度(没有额外的空间)
   *
   * If we apply P11_SampleOfflineDate to (0,1,2,...,n- 1) with k = n,at each iteration the
   * array is partitioned into the **partial permutation and remaining values**. Although
   * the subset that is returned is unique (it will be {0,1,...n-1}), all n! possible orderings
   * of the elements in the set occur with equal probability.
   * @param n
   * @return
   */
  public static List<Integer> solution(int n) {
    List<Integer> list = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      list.add(i);
    }

    /*
    实现.
    Random random = new Random(31);
    for (int i = 0; i < k; i++) {
      // random.nextInt(given.size() - i) + i就是 [i, size - 1]
      Collections.swap(given, i, random.nextInt(given.size() - i) + i);
    }
    return given.subList(0, k);
     */
    List<Integer> result = P11_SampleOfflineData.solution(list, list.size());

    return result;
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    List<Integer> result = solution(list.size());
    System.out.println(result);

    Object o = null;
    System.out.println("o=" + o);

  }

}
