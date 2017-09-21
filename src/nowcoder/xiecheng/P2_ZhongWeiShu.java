package nowcoder.xiecheng;

import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/21 19:41.
 * Description:
 */
public class P2_ZhongWeiShu {

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

    if (m == 0 && n == 0) {
      System.out.println(0);
      return;
    }

    if (m == 0 && n == 1) {
      System.out.println(second[0]);
      return;
    }

    if (m == 1 && n == 0) {
      System.out.println(first[0]);
      return;
    }

    if (m == 1 && n == 1) {
      System.out.println((first[0] + second[0]) / 2.0);
      return;
    }

    i = 0;
    int j = 0;

    boolean odd = (m + n) % 2 == 1 ? true : false; // 是否奇数.
    int count = 0; // 统计的个数.
    int prev = 0;
    int current = 0;
    int total = m + n;
    while (i < n && j < m) {
      if (first[i] < second[j]) {
        prev = first[i];
        i++;
        if (i < m)
          current = first[i];
      } else {
        prev = second[j];
        j++;
        if (j < n)
          current = second[j];
      }

      count++;

      if (odd) { // 奇数.
        if (count == total / 2) {
          System.out.println(current);
          break;
        }
        // 偶数.
      } else {
        if (count == total / 2) {
          System.out.println((prev + current) / 2.0);
          break;
        }
      }
    }

    if (i == n) {
      while (i < n) {
        prev = first[i];
        i++;
        if (i < n) {
          current = first[i];
        }

        count++;
        if (odd) { // 奇数.
          if (count == total / 2) {
            System.out.println(current);
            break;
          }
          // 偶数.
        } else {
          if (count == total / 2) {
            System.out.println((prev + current) / 2.0);
            break;
          }
        }

      }
    } else if (j == m) {
      while (j < m) {
        prev = first[j];
        j++;
        if (j < m) {
          current = second[j];
        }

        count++;
        if (odd) { // 奇数.
          if (count == total / 2) {
            System.out.println(current);
            break;
          }
          // 偶数.
        } else {
          if (count == total / 2) {
            System.out.println((prev + current) / 2.0);
            break;
          }
        }
      }
    }

  }
}
