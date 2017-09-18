package eopi.ch9_stackQueue;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Author by darcy
 * Date on 17-9-18 下午10:10.
 * Description:
 *
 * 检测三种括号是否匹配.
 *
 * A string over the characters "{,},(,),[,]" is said to be well-formed if the different
 * types of brackets match in the correct order. For example, "([]){()}" is well-formed,
 * as is "[()[]|()()|]". However, "{)" and "[()[]{()()" are not well-formed,  Write a
 * program that tests if a string made up of the characters '(', ')', '[', and"}' is well-formed.
 *
 * Hint: Which left parenthesis does a right parenthesis match with?
 *
 */
public class P3_TestBrackets {

  static Map<Character, Character> map = new HashMap<Character, Character>(){
    {
      put('(', ')');
      put('[', ']');
      put('{', '}');
    }
  };
  /**
   *
   * @param str
   * @return
   */
  public static boolean solution(String str) {
    Deque<Character> stack = new LinkedList<>();
    for (char ch : str.toCharArray()) {
      if (ch == '(' || ch == '[' || ch == '{') {
        stack.addLast(ch);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        Character last = stack.removeLast();
        if (map.get(last) != ch) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    String str = "[()]{[()]}";

    System.out.println(solution(str));
  }

}
