package jianzhioffer;

import java.util.ArrayList;

/**
 * Created by darcy
 * 2017/4/15--0:38
 * Description:
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵：
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class P20_InnerToOuterPrintNumber {

  public ArrayList<Integer> printMatrix(int[][] matrix) {

    if (matrix == null) {
      return null;
    }

    int widthLength = matrix.length;
    int heightLength = matrix[0].length;

    while (widthLength > 0) {

    }

    return null;

  }

  public static ArrayList<Integer> printMatrix2(int[][] matrix) {
    if (matrix.length == 0)
      return null;
    if (matrix[0].length == 0)
      return null;
    ArrayList<Integer> retArr = new ArrayList<Integer>();
    int left = 0, right = matrix[0].length - 1;//定义上下左右四个边界
    int up = 0, bottom = matrix.length - 1;
    while (left <= right && up <= bottom) {
      for (int i = left; i <= right && up <= bottom; i++) {//一定要控制边界情况
        retArr.add(matrix[up][i]);
      }
      up++;//循环完立刻更改值
      for (int i = up; i <= bottom && left <= right; i++) {
        retArr.add(matrix[i][right]);
      }
      right--;
      for (int i = right; i >= left && up <= bottom; i--) {
        retArr.add(matrix[bottom][i]);
      }
      bottom--;
      for (int i = bottom; i >= up && left <= right; i--) {
        retArr.add(matrix[i][left]);
      }
      left++;
    }
    return retArr;
  }

  public static void main(String[] args) {
    int[][] array = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16}
    };

    ArrayList<Integer> result = printMatrix2(array);
    System.out.println(result);

  }

}
