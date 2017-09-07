package jianzhioffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by darcy
 * 2017/4/16--1:02
 * Description:
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，
 * 请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
 * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 *
 * 如果放入的元素是顺序的，那么如果出栈出现了较大的元素，那么该元素后面就不会出现 小到大的出栈顺序.
 *
 *
 * 解体思路:
 * 用一个栈来存储数据，将pushA数据一次压入栈，如果栈顶的值与popA当前位置的值相等，
 * 则弹出栈。最后如果栈为空则OK。
 *
 */
public class P22_OutSequenceOfAStack {

  Deque<Integer> stack;

  // 思路: 如果入栈的中间某个元素已经出栈，
  // 那么该元素在入栈序的前面的元素就不会在出栈序中维持和入栈序中相同的次序关系；
  // TODO: need to fix it.
  public boolean IsPopOrder(int[] pushA, int[] popA) {

    stack = new LinkedList<>();
    int inStackIndex = 0;
    int outStackIndex = 0;
    int sequenceLength = pushA.length;

    while (true) {
      if (outStackIndex >= sequenceLength || inStackIndex >= sequenceLength) {
        break;
      }

      if (stack.size() != 0) {
        if (popA[outStackIndex] == stack.peekLast()) {
          stack.removeLast();
          outStackIndex++;
        } else {
          for (int i = inStackIndex; i < sequenceLength; i++) {
            if (pushA[inStackIndex] != popA[outStackIndex]) {
              stack.addLast(pushA[inStackIndex]);
            }
          }
        }
      } else {

        // 感觉这里应该...
        stack.addLast(pushA[inStackIndex]);
        inStackIndex++;
        /*if (inStackIndex < sequenceLength) {
            stack.addLast(pushA[inStackIndex]);
            inStackIndex++;
        }*/
      }
    }

    if (inStackIndex == sequenceLength && outStackIndex == sequenceLength) {
      return true;
    }

    return false;
  }

  public static boolean IsPopOrderX(int[] sequence, int[] output) {
    Stack<Integer> stack = new Stack<>();
    int outputIndex = 0;
    for (int i = 0; i < sequence.length; i++) {
      stack.push(sequence[i]);
      // 中间可能出现元素全部都出栈了.
      while (!stack.isEmpty() && stack.peek() == output[outputIndex]) {
        stack.pop();
        outputIndex++;
      }
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    int[] sequence = {1, 2, 3, 4, 5, 6, 7};
    int[] output = {3, 4, 2, 1, 7, 6, 5};
    boolean result = IsPopOrderX(sequence, output);
    System.out.println(result);

    output = new int[]{6, 5, 3, 4, 2, 1, 7};
    boolean result2 = IsPopOrderX(sequence, output);
    System.out.println(result2);
  }

  /*
      上一种方法的fix;
   */
  public boolean IsPopOrderFixed(int[] pushA, int[] popA) {

    if (pushA == null) {
      throw new RuntimeException("input array is null");
    }

    int inStackIndex = 0;
    int outStackIndex = 0;
    int sequenceLength = pushA.length;

    stack = new LinkedList<>();
    stack.addLast(pushA[inStackIndex++]);

    while (inStackIndex < sequenceLength || outStackIndex < sequenceLength) {
      if (popA[outStackIndex] == stack.peekLast()) {
        stack.removeLast();
        outStackIndex++;
      } else {
        if (inStackIndex < sequenceLength) {
          stack.addLast(pushA[inStackIndex++]);
        } else {
          return false;
        }
      }
    }
    return true;
  }

  /*
      TODO: understand it...
   */
  public boolean IsPopOrder2(int[] pushA, int[] popA) {
    if (pushA.length == 0 || popA.length == 0) {
      return false;
    }

    stack = new LinkedList<>();

    int popIndex = 0;
    for (int i = 0; i < pushA.length; i++) {
      stack.addLast(pushA[i]);
      while (!stack.isEmpty() && stack.peekLast() == popA[popIndex]) {
        stack.removeLast();
        popIndex++;
      }
    }

    return stack.isEmpty();
  }


  /* from internet
  https://www.nowcoder.com/questionTerminal/d77d11405cc7470d82554cb392585106
   */
  public boolean IsPopOrder3(int[] pushA, int[] popA) {
    if (pushA.length == 0 || popA.length == 0)
      return false;
    Stack<Integer> s = new Stack<Integer>();
    //用于标识弹出序列的位置
    int popIndex = 0;
    for (int i = 0; i < pushA.length; i++) {
      s.push(pushA[i]);
      //如果栈不为空，且栈顶元素等于弹出序列
      while (!s.empty() && s.peek() == popA[popIndex]) {
        //出栈
        s.pop();
        //弹出序列向后一位
        popIndex++;
      }
    }
    return s.empty();
  }

  /*
  链接：https://www.nowcoder.com/questionTerminal/d77d11405cc7470d82554cb392585106

  */
  public boolean IsPopOrder4(int[] pushA, int[] popA) {
    if (pushA.length == 0 || popA.length == 0)
      return false;
    ArrayList<Integer> list = new ArrayList<Integer>();
    int j = 0;
    for (int i = 0; i < pushA.length; i++) {
      if (pushA[i] != popA[j])
        list.add(pushA[i]);
      else
        j++;
    }
    for (int i = list.size() - 1; i >= 0; i--) {
      if (list.get(i) != popA[j])
        return false;
      j++;
    }
    return true;
  }


}
