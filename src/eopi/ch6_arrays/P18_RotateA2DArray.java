package eopi.ch6_arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-17 下午10:24.
 * Description:
 *
 * 二维矩阵顺时针方向旋转90度.
 *
 * 1   2  3  4        13 9 5 1
 * 5   6  7  8    =>  14 10 6 2
 * 9  10  11 12   =>  15 11 7 3
 * 13 14 15 16        16 12 8 4
 *
 */
public class P18_RotateA2DArray {

  /**
   * 创建一个copy array, 第 n-i-1 行是转换后的第i列.
   * @param array
   */
  public static void bruteForceSolution(int[][] array) {

    int[][] copy = new int[array.length][array.length];

    for (int i = array.length - 1; i >= 0; i--) {

    }
  }

  /**
   *
   * @param squareMatrix
   */
  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    final int matrixSize = squareMatrix.size() - 1;
    for (int i = 0; i < (squareMatrix.size() / 2); ++i) {
      // [i, matrixSize - i - 1]总共matrixSize - 2i个元素.
      // 4要变换的格式分别是 3, 1,
      for (int j = i; j < matrixSize - i; ++j) {
        // 左下角元素.
        int temp1 = squareMatrix.get(matrixSize - j).get(i);
        // 右下角元素.
        int temp2 = squareMatrix.get(matrixSize - i).get(matrixSize - j);
        // 右上角.
        int temp3 = squareMatrix.get(j).get(matrixSize - i);
        // 左上角.
        int temp4 = squareMatrix.get(i).get(j) ;

        // change n - 1个元素.
        squareMatrix.get(i).set(j, temp1);
        squareMatrix.get(matrixSize - j).set(i, temp2);
        squareMatrix.get(matrixSize - i).set(matrixSize - j, temp3);
        squareMatrix.get(j).set(matrixSize - i, temp4);
      }
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> list = new ArrayList<>();
    list.add(Arrays.asList(1, 2, 3, 4));
    list.add(Arrays.asList(5, 6, 7, 8));
    list.add(Arrays.asList(9, 10,11, 12));
    list.add(Arrays.asList(13, 14, 15, 16));

    for (List<Integer> row : list) {
      System.out.println(row);
    }
    System.out.println("-----------------");
    rotateMatrix(list);
    for (List<Integer> row : list) {
      System.out.println(row);
    }
  }


}
