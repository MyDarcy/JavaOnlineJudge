package nowcoder.mi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/18 19:26.
 * Description:
 */
public class StringChange {

  public static String BLACKCHAR = "_";

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    List<String> list = new LinkedList<>();
    String line = null;
    while (input.hasNextLine()) {
      line = input.nextLine().trim();
      list.add(line);
    }

    input.close();

    for (String str : list) {
      String result = solution(str);
      System.out.println(result);
    }

    /*StringBuilder sb = new StringBuilder("abcd");
    sb.insert(sb.length() - 1, "_");
    System.out.println(sb);*/
  }

  /**
   * 只会有大小写字母和数字.
   *
   * @param str
   * @return
   */
  private static String solution(String str) {
    if (str == null || str.length() == 0) {
      return "";
    }

    StringBuilder sb = new StringBuilder(BLACKCHAR);
    sb.append(Character.toUpperCase(str.charAt(0)));
    for (int i = 1; i < str.length(); i++) {
      // 当前是小写, 前一个是大写字母.
      char currentChar = str.charAt(i);
      if (Character.isLowerCase(currentChar)) {

        // 前面是两个大写字母.
        if (sb.length() >= 3 && Character.isUpperCase(str.charAt(i - 1)) && Character.isUpperCase(str.charAt(i - 2))) {
          // 上一个要插入_
          sb.insert(sb.length() - 1, BLACKCHAR);
          sb.append(Character.toUpperCase(currentChar));
          // 前一个是数字.
        } else if (Character.isDigit(str.charAt(i - 1))) {
          sb.append(BLACKCHAR);
          sb.append(Character.toUpperCase(currentChar));

          // 其他.
        } else {
          sb.append(Character.toUpperCase(currentChar));
        }
      } else if (currentChar == '.') {
        sb.append(BLACKCHAR);
        // 大写字母
      } else if (Character.isUpperCase(currentChar)) {
        // 前一个是小写字母.
        if (sb.length() >= 2 && Character.isLowerCase(str.charAt(i - 1))) {
          sb.append(BLACKCHAR);
          sb.append(currentChar);

          //前一个是数字.
        } else if (Character.isDigit(str.charAt(i - 1))) {

          sb.append(BLACKCHAR);
          sb.append(currentChar);


        } else {
          sb.append(currentChar);
        }

        // 数字.
      } else if (Character.isDigit(currentChar)) {
        if (Character.isLetter(str.charAt(i - 1))) {
          sb.append("_");
        }

        sb.append(currentChar);
      }
    }

    sb.append(BLACKCHAR);
    return sb.toString();
  }


}
/*
a
my.ABC
simple.HelloService
MY.ASTParser12
 */