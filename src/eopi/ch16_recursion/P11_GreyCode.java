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

  public static void main(String[] args) {
    List<Integer> result = solution(4);
    System.out.println(result);
  }


}
