package nowcoder.shangtang;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/10/12 20:34.
 * Description:
 *
 * 图片处理后有噪声.
 *
 * 全部被1包围的0是可以认为1, 底部联通的1则认为是陆地面积.
 * 最底部的一条边都是陆地.
 * 求总的陆地面积.
 *
 */
public class Main2 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int m = input.nextInt();
    int n = input.nextInt();
    int[][] array = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        array[i][j] = input.nextInt();
      }
    }
    input.close();


  }

}
