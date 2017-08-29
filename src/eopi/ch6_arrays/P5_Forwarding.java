package eopi.ch6_arrays;

/**
 * Author by darcy
 * Date on 17-8-28 下午5:05.
 * Description:
 *
 * 数组上的每个元素都代表从此位置出发,最多能走多少步, 检测一个数组所代表的序列, 能否走到末尾。
 *
 * Write a program which takes an array of n integers, where A[i] denotes the maximum
 * you can advance from index i, and returns whether it is possible to advance to the
 * last index starting from the beginning of the array.
 *
 * Hint: Analyze each location, starting from the beginning.
 *
 * For example, let A = (3,3,1,0,2,0,1)
 * represent the board game, i.e., the ith entry in A is the maximum we can advance
 * from i. Then the game can be won by the following sequence of advances through
 * A: take 1 step from A[0] to A[1], then 3 steps from A[l] to A[4], then 2 steps from
 * A[4] to A[6], which is the last position. Note that A[0] = 3 > 1, A[l] = 3 > 3, and
 * A[4] = 2 > 2, so all moves are valid. If A instead was (3, 2,0,0, 2,0,1), it would not
 * possible to advance past position 3, so the game cannot be won.
 *
 */
public class P5_Forwarding {

  /**
   *
   *
   * 时间复杂度O(n),空间复杂度O(1)
   *
   * @param array
   * @return
   */
  public static boolean solution(int[] array) {

    int canReach = 0;
    int last = array.length - 1;
    // 要达到的是last, 所以canReach < last, 等于的话就已经到达了.
    for (int i = 0; i <= canReach && canReach < last; i++) {
      canReach = Math.max(canReach, i + array[i]);
    }

    return canReach >= last;

  }

  public static void main(String[] args) {
    int[] array = new int[]{3, 3, 1, 0, 2, 0, 1};
    System.out.println(solution(array));

    array = new int[]{3, 2, 0, 0, 2, 0, 1};
    System.out.println(solution(array));

  }

}
