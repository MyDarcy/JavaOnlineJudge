package eopi.ch12_search;

import java.util.Arrays;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-9-26 上午10:34.
 * Description:
 * <p>
 * [0, n-1]中共有n个数字, 其中一个数字出现了2次, 一个数字没有出现.分别找出这两个数字.
 * <p>
 * You are given an array of n integers, each between 0 and n - 1, inclusive. Exactly
 * one element appears twice, implying that exactly one number between 0 and n- 1
 * is missing from the array. How would you compute the duplicate and missing
 * numbers?
 * <p>
 * Hint: Consider performing multiple passes through the array.
 */
public class P12_10_FindDuplicateAndMissingNumber {

  public static class MissingAndDuplicate {
    int duplicate;
    int missing;

    public MissingAndDuplicate(int duplicate, int missing) {
      this.duplicate = duplicate;
      this.missing = missing;
    }
  }

  /**
   * list中所有数字和[0...n-1]所有数字的亦或运算的结果duplicate & missing.
   * 最低位为1一定就是duplicate或者missing中的一个元素.
   * <p>
   * 然后求出最低位为1所代表的值 = a.
   * <p>
   * 然后再次亦或所有跟a与运算的结果不为0的元素{[0...n] 和 list里面所有.}
   * 如果该位是duplicate提供的, 那么这次亦或的结果就是duplicate值. 要不然就是missing值.
   *
   * @param list
   * @return
   */
  public static MissingAndDuplicate solution(List<Integer> list) {

    int missingXORDuplicate = 0;

    // 计算[0, n-1]所有数字的亦或以及 list中所有元素的亦或.
    // 计算出来的结果就是 duplicate ^ missing 因为重复的元素出现了3次, 而缺失的元素出现了一次.
    for (int i = 0; i < list.size(); i++) {
      int temp = i ^ list.get(i);
      missingXORDuplicate ^= temp;
    }

    // 求出最低位的bit.
    int leastBit = missingXORDuplicate & (~(missingXORDuplicate - 1));

    int missingOrDuplicate = 0;
    for (int i = 0; i < list.size(); i++) {
      if ((i & leastBit) != 0) {
        missingOrDuplicate ^= i;
      }

      if ((list.get(i) & leastBit) != 0) {
        missingOrDuplicate ^= list.get(i);
      }
    }

    for (int i = 0; i < list.size(); i++) {
      // missingOrDuplicate是重复的元素.
      if (missingOrDuplicate == list.get(i)) {
        return new MissingAndDuplicate(missingOrDuplicate,
            missingOrDuplicate ^ missingXORDuplicate);
      }
    }

    // missingOrDuplicate是缺失的元素。
    return new MissingAndDuplicate(missingOrDuplicate ^ missingXORDuplicate,
        missingOrDuplicate);

  }

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 5, 6, 8, 9);
    MissingAndDuplicate result = solution(list);
    System.out.println("Duplicate:" + result.duplicate);
    System.out.println("missing:" + result.missing);

  }

}
