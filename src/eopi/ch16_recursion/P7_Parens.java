package eopi.ch16_recursion;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午5:29.
 * Description:
 *
 * 生成()的的字符串.
 *
 *
 * Strings in which parens are matched are defined by the following three rules:
 * • The empty string, "", is a string in which parens are matched.
 * • The addition of a leading left parens and a trailing right parens to a string in
 * which parens are matched results in a string in which parens are matched. For
 * example, since "(())()" is a string with matched parens, so is "((())())".
 * • The concatenation of two strings in which parens are matched is itself a string
 * in which parens are matched. For example, since "(())()" and "()" are strings
 * with matched parens, so is "(())()()".
 *
 * For example, the set of strings containing two pairs of matched parens
 * is ((()),()()), and the set of strings with three pairs of matched parens is
 * ((())), ()(()), (())(), ()()();
 *
 * Write a program that takes as input a number and returns all the strings with that
 * number of matched pairs of parens.
 *
 * Hint: Think about what the prefix of a string of matched parens must look like.
 *
 */
public class P7_Parens {

  /**
   * 暴力方法;
   *
   * 迭代2k个小括号组成的字符串, 判断其是否是匹配字符串。这样组成的字符串的个数是2^2k个.
   *
   *
   * @param nPairs
   * @return
   */
  public static List<String> bruteForceSolution(int nPairs) {

    return null;
  }

  /**
   * recursion方法。
   *
   * 在一个( )组成的字符串上, 如何才能使得添加新的(, )后仍然可能是()两两配对的.
   * 1. 如果添加左括号, 只要它剩余个数大于0就可以继续放入.能不能成功是右括号的放入时机.
   * 2. 如果添加右括号, 需要添加后的字符串仍然可能满足条件,那么剩余的左括号一定要比剩余的又括号多。这样
   * 当前放入的右括号一定和已经存在于字符串中的左括号配对成功。
   *
   * We will ensure that as each additional character is added, the resulting string has
   * the potential to be completed to a string with k pairs of matched parens.
   *
   * How can we extend that string with an additional character so that the resulting
   * string can still be completed to a string with k pairs of matched parens?
   * • If we add a left parens, and still want to complete the string to a string with k
   * pairs of matched parens, it must be that the number of left parens we need is
   * greater than 0.
   * • If we add a right parens, and still want to complete the string to a string with
   * k pairs of matched parens, it must be that the number of left parens we need is
   * less than the number of right parens (i.e., there are unmatched left parens in the string).
   *
   * 复杂度:
   * The number C(k) of strings with k pairs of matched parens grows very rapidly with
   * k. Specifically, it can be shown that C(k + 1) = sum{C(k, i) / (k + 1), i = 0...k}, which solves to
   * (2k)!/((k!(k + 1)!).
   *
   *
   * @param nPairs
   * @return
   */
  public static List<String> solution(int nPairs) {
    List<String> result = new LinkedList<>();
    solution(nPairs, nPairs, "", result);
    return result;
  }

  private static void solution(int remainingLeftParens, int remainingRightParens, String inter, List<String> result) {
    if (remainingLeftParens == 0 && remainingRightParens == 0) {
      result.add(inter);
      return;
    }
    System.out.println(remainingLeftParens + " " + remainingRightParens);

    if (remainingLeftParens > 0) {
      solution(remainingLeftParens - 1, remainingRightParens, inter + "(", result);
    }

    // 左个数 < 右个数, 才能放一个右括号 ) ;
    // 在本层栈中, remainLeftParens和rightParen,还有inter值都是不变的.
    // 第一次执行到这里的时候, 当前栈的下一层栈中remainLeftParens = 0;
    // 在当前栈中, remainLeftParens = 1;而remainRightParens = 4;
    if (remainingLeftParens < remainingRightParens) {
      solution(remainingLeftParens, remainingRightParens - 1, inter + ")", result);
    }

  }


  public static void main(String[] args) {
    List<String> result = solution(4);
    System.out.println(result);
    Collections.sort(result);
    System.out.println();
    System.out.println(result);
  }
}
