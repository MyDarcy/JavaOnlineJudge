package nowcoder.meituan;

import java.util.IllegalFormatException;

/**
 * Created by hzq19
 * Date on 2017/10/24 15:41.
 * Description:
 *
 * 两个链表.
 */
public class Main3 {

  /**
   *
   * @param ip
   * @return
   */
  public static boolean isValidIp(String ip) {
    String[] array = ip.split(":");
    for (int i = 0; i < array.length; i++) {
      try {
        Integer part = Integer.parseInt(array[i]);
        if (part >= 0 && part <= 255) {
          continue;
        } else {
          return false;
        }
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    String ip1 = "255:255:255:0";
    System.out.println(isValidIp(ip1));

    String ip2 = "2a:255:255:0";
    System.out.println(isValidIp(ip2));
  }
}
