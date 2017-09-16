package nowcoder.neteasy;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/16 20:48.
 * Description:
 *
 * 输入一个二维数组，元素为非负整数，每行和每列都是从小到大有序的，请用C/C++实现最优算法判断一个元素是否在二维数组中。
 输入描述:
 第1行：数组行数m，数组列数n，空格分隔 第2行：第1行数据，空格分隔 第3行：第2行数据，空格分隔 第4行：第3行数据，空格分隔
 ………… 第m+1行：要找的数字  例如： 4 5 1 2 3 4 5 2 3 4 5 6 3 4 5 6 7 4 5 6 7 8 6
 输出描述:
 1表示找到，0表示未找到 例如： 1  如果出现输入数据错误，如：行数或列数格式错误/小于等于0、或者行或列数据缺失、待检验数格
 式错误等，均输出： input error
 示例1
 输入

 4 5
 1 2 3 4 5
 2 3 4 5 6
 3 4 5 6 7
 4 5 6 7 8
 6
 输出

 1
 示例2
 输入

 2 3
 1 4 7
 2 5 8
 3
 输出

 0
 示例3
 输入

 3 3
 1 2 3
 4 5 6
 7 8
 10
 输出

 input error
 */
public class IsInMatrix {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] mn = input.nextLine().trim().split("\\s+");
    int n = 0;
    int m = 0;
    try {
      n = Integer.parseInt(mn[0]);
      m = Integer.parseInt(mn[1]);

      if (n <= 0 || m <= 0) {
        System.out.println("input error");
        return;
      }
    } catch (NumberFormatException e) {
      System.out.println("input error");
      return;
    }

    String line = null;
    int[][] array = new int[n][m];
    String[] tempStrs = null;
    int lineCount = 0;
    while (true) {
      line = input.nextLine();
      tempStrs = line.split("\\s+");
      if (tempStrs.length != m) {
        System.out.println("input error");
        return;
      }
      try {
        for (int i = 0; i < m; i++) {
          array[lineCount][i] = Integer.parseInt(tempStrs[i]);
        }
      } catch (NumberFormatException e) {
        System.out.println("input error");
        return;
      }
      lineCount++;
      if (lineCount == n) {
        break;
      }
    }

    int target = 0;
    try {
      target = Integer.parseInt(input.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("input error");
      return;
    }
    input.close();

    int startI = n - 1;
    int startJ = 0;
    while (startI >= 0 && startJ < m) {
      if (array[startI][startJ] > target) {
        startI--;
      } else if (array[startI][startJ] < target) {
        startJ++;
      } else {
        System.out.println(1);
        return;
      }
    }
    System.out.println(0);
  }

  /*
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int m = input.nextInt();
    int[][] array = new int[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // 读入数据.
        array[i][j] = input.nextInt();
      }
    }

    int target = input.nextInt();

    input.close();

    int startI = n - 1;
    int startJ = 0;
    while (startI >= 0 && startJ < m) {
      if (array[startI][startJ] > target) {
        startI--;
      } else if (array[startI][startJ] < target) {
        startJ++;
      } else {
        System.out.println(1);
        return;
      }
    }
    System.out.println(0);
  }*/

}

/*
2 3
1 4 7
2 5 8
3


4 5
1 2 3 4 5
2 3 4 5 6
3 4 5 6 7
4 5 6 7 8
6

4 5
1 2 3 4 5
2 a 4 5 6
3 4 5 6 7
4 5 6 7 8
6


 */