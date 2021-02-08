package leetcode;

import java.util.*;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class P0020_E_ValidParentheses {
  /**
   * left元素入栈，遇到right元素出栈并和right元素比较是否相等；最后查看栈是否为空;
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    Set<Character> lefts = new HashSet<>(Arrays.asList('(', '[', '{'));
    Set<Character> rights = new HashSet<>(Arrays.asList(')', ']', '}'));
    Map<Character, Character> map = new HashMap<Character, Character>() {
      {
        put(')', '(');
        put(']', '[');
        put('}', '{');
      }
    };

    LinkedList<Character> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      if (lefts.contains(currentChar)) {
        stack.addLast(currentChar);
      } else if (rights.contains(currentChar)) {
        if (stack.isEmpty()) return false;
        if (!stack.removeLast().equals(map.get(currentChar))) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public boolean isValid2(String s) {
    Stack<Character> stack = new Stack<Character>();
    for (char c : s.toCharArray()) {
      if (c == '(')
        stack.push(')');
      else if (c == '{')
        stack.push('}');
      else if (c == '[')
        stack.push(']');
      else if (stack.isEmpty() || stack.pop() != c)
        return false;
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    P0020_E_ValidParentheses instance = new P0020_E_ValidParentheses();
    System.out.println(instance.isValid("()"));
    System.out.println(instance.isValid("()[]{}"));
    System.out.println(instance.isValid("(]"));
    System.out.println(instance.isValid("([)]"));
    System.out.println(instance.isValid("{[]}"));
  }
}
