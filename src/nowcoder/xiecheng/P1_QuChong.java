package nowcoder.xiecheng;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/21 19:37.
 * Description:
 */
public class P1_QuChong {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String line = input.nextLine().trim();
    Map<Character, Integer> exists = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < line.length(); i++) {
      if (!exists.containsKey(line.charAt(i))) {
        sb.append(line.charAt(i));
        exists.put(line.charAt(i), 1);
      }
    }

    System.out.println(sb.toString());

  }

}
