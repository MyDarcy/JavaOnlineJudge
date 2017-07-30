package ctci5th.chapter8.section9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Author by darcy
 * Date on 17-7-30 上午11:37.
 * Description:
 * n对括号的全部有效组合
 */
public class P906_ProperBracketPermutation {

  /**
   * n == 3: ()()(), ()(()), (()()), (())(), ((()))
   * n == 2: ()(), (())
   * ()() -> ()()() 字符串开头插入一对括号
   * ()() -> (())() 第一个(后面插入一对括号
   * ()() -> ()(()) 第二个(后面插入一对括号 * 重复
   *
   * (()) -> ()(()) 字符串开头插入一对括号 * 重复
   * (()) -> (()()) 第一个(后面插入一对括号
   * (()) -> ((())) 第二个(后面插入一对括号
   *
   *
   * @param n
   * @return
   */
  public static Set<String> properBracets(int n) {
    Set<String> result = new HashSet<>();
    if (n == 0) {
      result.add("");
    } else {
      Set<String> temp = properBracets(n - 1);
      for (String str : temp) {
        for (int i = 0; i < str.length(); i++) {
          if (str.charAt(i) == '(') {
            // set中已经包含了去重.
            result.add(buildBrackets(str, i));
          }
        }
        // 开头加()的情况并没有处理.
        if (!result.contains("()" + str)) {
          result.add("()" + str);
        }
      }
    }
    return result;
  }

  private static String buildBrackets(String str, int index) {
    return str.substring(0, index + 1) + "()" + str.substring(index + 1);
  }


  /**
   * 从头开始构造字符串, 逐一加入左括号和右括号，只要字符串仍然是符合要求的．
   * 左括号：只要左括号还没有用完，就可以插入左括号
   * 有括号：只要没有出现右括号数目>=左括号数目. 就可以插入右括号.
   *
   * 维护一个剩余左右括号的计数．
   * @param list
   * @param leftRem
   * @param rightRem
   * @param str
   * @param count
   */
  public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count) {
    if (leftRem < 0 || rightRem < leftRem) return; // invalid state

    if (leftRem == 0 && rightRem == 0) { /* all out of left and right parentheses */
      String s = String.copyValueOf(str);
      list.add(s);
    } else {
      if (leftRem > 0) { // try a left paren, if there are some available
        str[count] = '(';
        addParen(list, leftRem - 1, rightRem, str, count + 1);
      }
      if (rightRem > leftRem) { // try a right paren, if there抯 a matching left
        str[count] = ')';
        addParen(list, leftRem, rightRem - 1, str, count + 1);
      }
    }
  }

  public static ArrayList<String> generateParens(int count) {
    char[] str = new char[count*2];
    ArrayList<String> list = new ArrayList<String>();
    addParen(list, count, count, str, 0);
    return list;
  }


  public static void main(String[] args) {
    Set<String> strings = properBracets(2);
    System.out.println(strings.size());
    System.out.println(strings);

    ArrayList<String> list = generateParens(3);
    for (String s : list) {
      System.out.println(s);
    }
    System.out.println(list.size());
  }

}
