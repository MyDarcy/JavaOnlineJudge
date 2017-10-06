package eopi.ch18_greedy;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-10-6 上午11:39.
 * Description:
 *
 * 找到字符串序列中出现了超过一半的某个字符串.
 *
 */
public class P18_5_FindTheMajorityElements {

  /**
   * 计数的方式.
   *
   * @param iterable
   * @return
   */
  public static String solution(Iterator<String> iterable) {
    String result = "";
    int count = 0;
    String iter = null;
    while (iterable.hasNext()) {
      iter = iterable.next();
      if (count == 0) {
        result = iter;
        count++;
      } else if (result.equals(iter)) {
        ++count;
      } else {
        --count;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    List<String> list = Arrays.asList("b", "a", "c", "a", "a", "b", "a", "a", "c", "a");
    String result = solution(list.iterator());
    System.out.println(result);
  }

}
