package nowcoder.jd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/8 19:57.
 * Description:
 * <p>
 * 合法的括号序列
 */
public class BracketNumber {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String line = input.nextLine().trim();
    int result = solution2(line);
    System.out.println(result);

  }

  private static int solution2(String line) {
    /*if (line.equals("")) {
      return 1;
    }

    int left = 0;
    int right = 0;
    char prev = '0';
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '(') {
        if (right != 0) {
          list.add(right);
          right = 0;
          left -= right;
        }
        left++;
        prev = '(';
      }

      if (line.charAt(i) == ')') {
        right++;
      }

    }
    return 1;*/


    // ((())(()))()()
    int ans = 1, cnt = 0;
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '(') {
        cnt++;
      } else {
        ans *= cnt;
        cnt--;
      }
    }
    return ans;

  }

  private static int solution(String line) {
    if (line.equals("")) {
      return 1;
    }
    char prev = '(';
    int count = 0;
    int left = 0;
    int right = 0;
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < line.length(); i++) {
      if (line.charAt(i) == '(') {
        count++;
        left++;
        continue;
      }

      if (line.charAt(i) == ')' && count != 0) {
        list.add(count);
        count = 0;
      }
    }
    /*System.out.println(list);
    return 1;*/

    System.out.println(list);
    int result = 1;
    for (int i = 0; i < list.size(); i++) {
      result *= mi(list.get(i));
    }
    return result;
  }

  private static int mi(Integer integer) {
    int result = 1;
    while (integer > 0) {
      result *= integer;
      integer--;
    }
    return result;
  }
}
