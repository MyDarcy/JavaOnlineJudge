package nowcoder.neteasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/16 20:34.
 * Description:
 *
 * 用C语言实现一个程序，给定一个不重复的单词列表，找出所有的不重复的索引对 (I, j)，
 * 使得这两个位置的单词连接起来是一个回文串。输出索引对的总数

 例1
 words = [“ccd”, “dd”, “dcc”]
 索引对是 [0, 2], [2, 0]
 回文串是”ccddcc”, “dccccd”
 输出 2
 例2
 words = [“lls”, “s”, “sssll”, “”]
 索引对是 [0, 2], [1, 0], [1, 3], [3, 1]
 回文串是”llssssll”, “slls”, “s”, “s”
 输出 4
 输入描述:
 第一行是整数 1 <= n <= 500，表示有多少个单词。  接下来是n行，每行是一个由小写字母组成的字符串或空字符串，
 每个字符串长度1 <= k <= 300。
 输出描述:
 输出整数m，表示有多少个索引对
 */
public class PadroinicString {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int lineNumber = Integer.parseInt(input.nextLine().trim());
    List<String> list = new ArrayList<>(lineNumber);
    for (int i = 0; i < lineNumber; i++) {
      list.add(input.nextLine());
    }
    input.close();

    // System.out.println(list);

    int count = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < lineNumber; i++) {
      for (int j = 0; j < lineNumber; j++) {
        if (i == j) {
          continue;
        }
        if (isPandronic(sb.append(list.get(i)).append(list.get(j)))) {
          count++;
        }

        sb.delete(0, sb.length());
      }
    }
    System.out.println(count);
  }

  private static boolean isPandronic(StringBuilder sb) {
    if (sb.length() <= 1) {
      return true;
    }
    int low = 0;
    int high = sb.length() - 1;
    while (low < high) {
      if (sb.charAt(low) != sb.charAt(high)) {
        return false;
      }
      low++;
      high--;
    }
    return true;
  }

}

/*
3
ccd
dd
dcc


4
lls
s
sssll



 */