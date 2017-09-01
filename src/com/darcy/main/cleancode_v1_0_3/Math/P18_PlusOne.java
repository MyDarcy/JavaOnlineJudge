package com.darcy.main.cleancode_v1_0_3.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-1 下午9:38.
 * Description:
 *
 * 给定一个可能很大的数字,执行+1操作.
 *
 * Given a number represented as an array of digits, plus one to the number.
 *
 * Example Questions Candidate Might Ask:
 * Q: Could the number be negative?
 * A: No. Assume it is a non-negative number.
 * Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or
 * [2,1]?
 * A: The digits are stored such that the most significant digit is at the head of the list.
 * Q: Could the number contain leading zeros, such as [0,0,1]?
 * A: No.
 */
public class P18_PlusOne {

  public static List<Integer> solution(List<Integer> list) {

    for (int i = list.size() - 1; i > 0; i--) {
      // 末位+1,其他位是根据进位进行计算。
      if (i == list.size() - 1) {
         list.set(i, list.get(i) + 1);
      }
      if (list.get(i) == 10) {
        list.set(i - 1, list.get(i - 1) + 1);
        list.set(i, 0);
      }
    }

    // 进位到最前面了,那么说明后面的都是0了。
    // 那么可以直接 append 0,然后首位设置为 1;
    if (list.get(0) == 10) {
      list.set(0, 0);
      list.add(0, 1);
    }
    return list;
  }


  public static void solution2(List<Integer> digits) {
    for (int i = digits.size() - 1; i >= 0; i--) {
      int digit = digits.get(i);
      // 最末尾或者说有其他进位的话，当前 < 9 都会使得程序返回，不会再有进位了。
      if (digit < 9) {
        digits.set(i, digit + 1);
        return;
      } else {
        // 进位后为0;
        digits.set(i, 0);
      }
    }
    digits.add(0);
    digits.set(0, 1);
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(9);
    list.add(9);
    list.add(9);
    list.add(8);

    List<Integer> result = solution(list);
    System.out.println(result);

    List<Integer> list2 = new ArrayList<>(list);
    solution2(list2);
    System.out.println(list2);
  }

}
