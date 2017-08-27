package ctci5th.chapter8.section9;

import java.util.ArrayList;

/**
 * Author by darcy
 * Date on 17-7-30 下午4:36.
 * Description:
 * 8皇后问题.
 */
public class P909_EightQueen {

  private static final int GRID_SIZE = 8;

  /**
   * columns[i] = j 表示有个皇后位于i行j列.
   *
   * 同样, 子问题求解, 第一个皇后分别放于(0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7),
   * 那么第一个皇后放置于(0, 0), 那么第二个皇后的可放位置就是(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7),
   * [(1,0)跟第一个皇后同列, 去掉这种情况]
   *
   * 依次类推.
   *
   * @param row
   * @param columns
   * @param results
   */
  public static void solution(int row, Integer[] columns, ArrayList<Integer[]> results) {
    if (row == GRID_SIZE) {
      results.add(columns.clone());
    } else {
      for (int col = 0; col < GRID_SIZE; col++) {
        // 第row个皇后的可放位置.
        if (check(columns, row, col)) {
          columns[row] = col;
          // 第row+1个皇后的可放位置.
          solution(row + 1, columns, results);
        }
      }
    }
  }

  private static boolean check(Integer[] columns, int row, int col) {
    for (int row1 = 0; row1 < row; row1 ++) {
      int column1 = columns[row1];

      if (col == column1) {
        return false;
      }

      int rowDistance = row - row1;
      int columnDistance = Math.abs(col - column1);
      if (rowDistance == columnDistance) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Integer[] columns = {-1, -1, -1, -1, -1, -1, -1, -1};
    ArrayList<Integer[]> results = new ArrayList<>();
    solution(0, columns, results);
    System.out.println(results.size());
  }

}
