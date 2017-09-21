package nowcoder.xiecheng;

import com.sun.javafx.image.IntPixelGetter;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/21 19:41.
 * Description:
 */
public class P2_ZhongWeiShu2 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int n = input.nextInt();
    int i = 0;
    int[] first = new int[n];
    while (i < n) {
      first[i] = input.nextInt();
      i++;
    }

    int m = input.nextInt();
    int[] second = new int[m];
    i = 0;
    while (i < m) {
      second[i] = input.nextInt();
      i++;
    }
    input.close();
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int j = 0; j < n; j++) {
      queue.add(first[j]);
    }

    for (int j = 0; j < m; j++) {
      queue.add(second[j]);
    }

    boolean odd = (m + n) % 2 == 1 ? true : false;
    if (odd) {
      int index = 0;
      while (index < (m + n) / 2) {
        queue.poll();
      }
      System.out.println(queue.poll());
      return;
    } else {
      int index = 0;
      while (index < (m + n - 2) / 2) {
        queue.poll();
      }
      System.out.println((queue.poll() + queue.poll()) / 2.0);
      return;
    }

  }
}
