package com.darcy.main.cleancode_v1_0_3.Stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Author by darcy
 * Date on 17-9-3 下午10:56.
 * Description:
 *
 * 计算逆波兰表达式
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */
public class P40_EvaluateReversePolishNotation {

  private static final Set<String> OPERATORS =
      new HashSet<>(Arrays.asList("+", "-", "*", "/", "%"));

  /**
   *
   * @param tokens
   * @return
   */
  public static Integer solution(String[] tokens) {
    Stack<Integer> numbers = new Stack<>();
    for (int i = 0; i < tokens.length; i++) {
      if (OPERATORS.contains(tokens[i])) {
        Integer value2 = numbers.pop();
        Integer value1 = numbers.pop();
        numbers.push(eval(tokens[i], value1, value2));
      } else {
        numbers.push(Integer.parseInt(tokens[i]));
      }
    }
    return numbers.pop();
  }

  private static Integer eval(String ops, Integer value1, Integer value2) {
    switch (ops) {
      case "+":
        return value1 + value2;
      case "-":
        return value1 - value2;
      case "*":
        return value1 * value2;
      case "/":
        return value1 / value2;
      case "%":
        return value1 % value2;
      default:
        break;
    }
    return -1;
  }

  public static void main(String[] args) {
    String[] tokens = {"2", "1", "+", "3", "*"};
    Integer result = solution(tokens);
    System.out.println(result);


    tokens = new String[]{"4", "13", "5", "/", "+"};
    Integer result2 = solution(tokens);
    System.out.println(result2);

  }

}
