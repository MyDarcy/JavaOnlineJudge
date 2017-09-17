package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-17 下午9:33.
 * Description:
 * <p>
 * 输出数组顺时针spiral的结果.
 * <p>
 * 1 2 3
 * 4 5 6
 * 7,8,9   => 123698745
 * <p>
 * 1   2   3  4
 * 5   6   7  8
 * 9  10  11 12
 * 13 14  15 16  => 1234 8 12 16 15 14 13 9 5 6 7 11 10
 */
public class P17_ComputeTheSpiralOrderingOfA2dArray {

  /**
   * 顺时针方向一层一层的拨开.
   * i = 1, 顺时针方向4条边都剥去掉n-1个.
   * i = 2, 顺时针方向4条边都剥去掉n-3个.
   * i = 3, 顺时针方向4条边都剥去掉n-5个.
   * ...
   * <p>
   * 时间复杂度O(n^2),空间复杂度O(1);
   *
   * @param squareMatrix
   * @return
   */
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> spiralOrdering = new ArrayList<>();
    // 奇数的话,那么需要 n/2 + 1次.
    for (int offset = 0; offset < Math.ceil(0.5 * squareMatrix.size());
         ++offset) {
      matrixLayerlnClockwise(squareMatrix, offset, spiralOrdering);
    }
    return spiralOrdering;
  }

  private static void matrixLayerlnClockwise(List<List<Integer>> squareMatrix,
                                             int offset,
                                             List<Integer> spiralOrdering) {
    if (offset == squareMatrix.size() - offset - 1) {
      // squareMatrix has odd dimension, and we are at its center.
      spiralOrdering.add(squareMatrix.get(offset).get(offset));
      return;
    }

    // 上.
    for (int j = offset; j < squareMatrix.size() - offset - 1; ++j) {
      spiralOrdering.add(squareMatrix.get(offset).get(j));
    }
    // 右
    for (int i = offset; i < squareMatrix.size() - offset - 1; ++i) {
      spiralOrdering.add(
          squareMatrix.get(i).get(squareMatrix.size() - offset - 1));
    }
    // 下
    for (int j = squareMatrix.size() - offset - 1; j > offset; --j) {
      spiralOrdering.add(
          squareMatrix.get(squareMatrix.size() - offset - 1).get(j));
    }

    // 左.
    for (int i = squareMatrix.size() - offset - 1; i > offset; --i) {
      spiralOrdering.add(squareMatrix.get(i).get(offset));
    }
  }

  /**
   * (i,j)表示j行i列.
   *  The pair (i, j) denotes the entry in Column i and Row j. Let(x, y) be the next
   *  element to process. Initially we move to the right (incrementing x until (n-1, 0)
   *  is processed). Then we move down (incrementing y until(n-1,n-1) is processed).
   *  Then we move left (decrementing x until (0, n- 1) is processed). Then we move
   *  up (decrementing y until (0,1) is processed). Note that we stop at 1, not 0, since
   *  (0, 0) was already processed.
   *
   * 时间复杂度O(n^2), 空间复杂度O(1)
   * @param squareMatrix
   * @return
   */
  public static List<Integer> matrixInSpiralOrder2(List<List<Integer>> squareMatrix) {
    // 4个方向x,y的单次改变的值.
    // x, y 分别表示行和列.
    final int[][] SHIFT = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int dir = 0, x = 0, y = 0;
    List<Integer> spiralOrdering = new ArrayList<>();
    for (int i = 0; i < squareMatrix.size() * squareMatrix.size(); ++i) {
      spiralOrdering.add(squareMatrix.get(x).get(y));
      squareMatrix.get(x).set(y, 0);
      int nextX = x + SHIFT[dir][0], nextY = y + SHIFT[dir][1];
      // 限定x,y的可能范围.
      if (nextX < 0 || nextX >= squareMatrix.size() || nextY < 0
          || nextY >= squareMatrix.size()
          || squareMatrix.get(nextX).get(nextY) == 0) {
        dir = (dir + 1) % 4;
        nextX = x + SHIFT[dir][0];
        nextY = y + SHIFT[dir][1];
      }
      x = nextX;
      y = nextY;
    }
    return spiralOrdering;
  }

  public static void main(String[] args) {
    List<List<Integer>> list = new ArrayList<>();
    list.add(Arrays.asList(1, 2, 3, 4));
    list.add(Arrays.asList(5, 6, 7, 8));
    list.add(Arrays.asList(9, 10, 11, 12));
    list.add(Arrays.asList(13, 14, 15, 16));

    List<Integer> result = matrixInSpiralOrder2(list);
    System.out.println(result);
  }
}

/*
Variant: Given a sequence of integers P, compute a 2D array A whose spiral order is
P. (Assume the size of P is n^2 for some integer n.)

Variant: Write a program to enumerate the first n pairs of integers (a,b) in spiral order,
starting from (0,0) followed by (1,0). For example, if n = 10, your output should be
(0, 0), (1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0),(-1, 1), (0, 1), (1, 1), (2, 1).

Variant: Compute the spiral order for an m * n 2D array A.

Variant: Compute the last element in spiral order for an m X n 2D array A in O(1)
time.

Variant:Compute the kth element in spiral order for an mxn 2Darray A in O(1) time.
*/