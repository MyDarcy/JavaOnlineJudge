package nowcoder.toutiao;

import java.util.Scanner;

/**
 *
 */
public class Main2 {
  private static String s;
  private static int n, k;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String[] array = input.nextLine().trim().split("\\s+");
    n = Integer.parseInt(array[0]);
    k = Integer.parseInt(array[1]);
    s = input.nextLine();
    input.close();
    int max1 = deal('a', 'b');
    int max2 = deal('b', 'a');
    System.out.println(max(max1, max2));
  }

  private static int max(int a, int b) {
    return a > b ? a : b;
  }

  /**
   *
   * @param a
   * @param a1
   * @return
   */
  private static int deal(char a, char a1) // change a to a1
  {
    int L = 0, R = 0, change = 0, ans = 1;
    for (int i = 0; i < n; i++) {
      // 当前遍历到的字符为a, 然后变换次数小于阈值, 所以可以进行变换.
      // 等于目标值的话，那么就不需要变换了.
      if (s.charAt(i) == a) {
        if (change < k) {
          change++;
          R++;

          // change = k
        } else {
          // 找到等于待消除的值的index.
          while (L <= R && s.charAt(L) != a) {
            L++;
          }
          // 这里就相当于是change次数-1了.
          L++;
          R++;
        }
      } else {
        R++;
      }
      ans = Integer.max(ans, R - L);
    }
    return ans;
  }
}

/*
8 1
aabaabaa
5
 */