package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 下午10:09.
 * Description:
 *
 * nbit的 GrayCode意思是连续相邻的二进制整数只有一位是不同的.
 *
 * An n-bit Gray code is a permutation of {0,1, 2,...,2^n - 1) such that the binary rep
 * resentations of successive integers in the sequence differ in only one place. ie the
 * last and first elements must also differ in only one place.)
 */
public class P11_GreyCode {


  public static List<Integer> solution(int numberBits) {
    List<Integer> result = new ArrayList<>(Arrays.asList(0));
    solution(numberBits, new HashSet<Integer>(Arrays.asList(0)), result);
    return result;
  }

  private static boolean solution(int numberBits, HashSet<Integer> beenOccur, List<Integer> result) {
    if (result.size() == (1 << numberBits)) {
      return differsByOneBit(result.get(0), result.get(result.size() - 1));
    }

    for (int i = 0; i < numberBits; i++) {
      int previous = result.get(result.size() - 1);

      // 产生一位的不同.
      int candidate = previous ^ (1 << i);
      if (!beenOccur.contains(candidate)) {
        beenOccur.add(candidate);
        result.add(candidate);
        if (solution(numberBits, beenOccur, result)) {
          return true;
        }
        beenOccur.remove(candidate);
        result.remove(result.size() - 1);
      }
    }
    return false;
  }

  private static boolean differsByOneBit(Integer x, Integer y) {
    int bitDifference = x ^ y;
    return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
  }


  /**
   * 偏分析的方法.
   *
   * 例子 <00><01><11><10> 要想添加一位使得仍然满足题意; 那么
   * 先顺序的添加0得到   <000><001><011><010>
   * 然后逆序的添加1得到 <110><111><101><100>
   *组合起来的结果就是 <000><001><011><010><110><111><101><100>
   *
   *
   * Assuming we operate on integers that fit within the size of the integer word, the time
   * complexity T(n) satisfies T(n) = T(n-1) + O( 2^n-1). the time complexity is O(2^n).
   *
   * @param bitNumber
   * @return
   */
  public static List<Integer> efficientSolution(int bitNumber) {
    if (bitNumber == 0) {
      return new ArrayList<>(Arrays.asList(0));
    }


    List<Integer> result = efficientSolution(bitNumber - 1);

    // 前导1;注意左移动 n-1 位, 那么结果就是 n 位的.
    // 当result中只有0的时候, leadingOneBit = 1; 1 | 0 = 1, 添加到result中去.
    // 当result中只有0, 1的时候, leadingOneBit = 1 << 1 = 2;   2 | 1 = 11; 2 | 0 = 10
    // 然后集合中的数据集就变成了 0,1, 3, 2; 如此继续下去.
    int leadingBitOne = 1 << (bitNumber - 1);

    int i = result.size() - 1;
    for (; i >= 0; i--) {
      result.add(leadingBitOne | result.get(i));
    }

    return result;
  }

  public static void main(String[] args) {
    List<Integer> result = solution(4);
    System.out.println(result);

    System.out.println();

    System.out.println(efficientSolution(4));
  }


}
