package leetcode;

import java.util.Arrays;

/**
 * 之字型的转换；给定字符串摆成了给定行数的之字型，按行读取后去掉空字符后输出；
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Example 1:
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 *
 * Example 2:
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I     N
 * A   L S   I G
 * Y A   H R
 * P     I
 *
 */
public class P0006_M_ZigZagConvert {

  /**
   * amazing!!! 两个遍历过程模拟了之字型的走法；
   * @param s
   * @param numRows
   * @return
   */
  public String convert(String s, int numRows) {
    char[] charArray = s.toCharArray();
    int length = s.length();
    StringBuilder[] sbArray = new StringBuilder[numRows];
    for (int i = 0; i < sbArray.length ; i++) {
      sbArray[i] = new StringBuilder();
    }

    int i = 0;
    while (i < length) {
      // index < nunRows; 对列的操作，映射到某一行;
      for (int index = 0; index < numRows && i < length; index++) {
        sbArray[index].append(charArray[i++]);
      }

      // 倒数第二行到第二行;
      for (int index = numRows - 2; index >= 1 && i < length; index--) {
        sbArray[index].append(charArray[i++]);
      }
    }

    for (int j = 1; j < sbArray.length; j++) {
      sbArray[0].append(sbArray[j]);
    }

    return sbArray[0].toString();

  }

  public String convert2(String s, int numRows) {
    //
    if (numRows == 1) return s;
    StringBuilder ret = new StringBuilder();
    char[] c = s.toCharArray();
    int cycle = 2 * numRows - 2; // 一数列 numRows 个元素; 两整列之间 rowNums - 2 个;
    int n = s.length();
    for (int i = 0; i < numRows; i++) {
      // i+j 下一列同一位置处的元素; j可以认为是整列;
      for (int j = 0; j + i < n; j += cycle) {
        ret.append(c[i+j]);
        // 两整列之间的元素; j + cycle刚好是斜对角最上面的元素;
        if (i != 0 && i != numRows - 1 && j + cycle - i < n)
          ret.append(c[j + cycle - i]);
      }
    }
    return ret.toString();
  }

  public static void main(String[] args) {
    P0006_M_ZigZagConvert instance = new P0006_M_ZigZagConvert();
    String result = instance.convert("PAYPALISHIRING", 3);
    String result2 = instance.convert("PAYPALISHIRING", 4);
    System.out.println(result);
    System.out.println(result2);
  }

}
