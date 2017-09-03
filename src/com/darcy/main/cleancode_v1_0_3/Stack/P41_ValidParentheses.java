package com.darcy.main.cleancode_v1_0_3.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-9-3 下午11:11.
 * Description:
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and
 * "([)]" are not
 *
 */
public class P41_ValidParentheses {

  private static final Map<Character, Character> map =
      new HashMap<Character, Character>() {{
        put('(', ')');
        put('{', '}');
        put('[', ']');
      }};

  /**
   *
   * @param str
   * @return
   */
  public static boolean solution(String str) {
    Stack<Character> stack = new Stack<>();
    for (char c : str.toCharArray()) {
      if (map.containsKey(c)) {
        // 入的是左符号.
        stack.push(c);
        // 非左符号的判定是否和上一个入的符号匹配.
      } else if (stack.isEmpty() || !map.get(stack.pop()).equals(c)) {
        return false;
      }
    }

    // 简单的(((就是返回false的.
    return stack.isEmpty();
  }

}
