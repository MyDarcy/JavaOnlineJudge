package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  /**
   * 常规解法
   * @param s
   * @param numRows
   * @return
   */
  public String convert2(String s, int numRows) {
    if (numRows == 1) return s;
    StringBuilder ret = new StringBuilder();
    char[] c = s.toCharArray();
    int cycle = 2 * numRows - 2; // 一数列 numRows 个元素; 两整列之间 rowNums - 2 个; 首行元素所在的位置.
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

  /**
   * official solution
   * Iterate through ss from left to right, appending each character to the appropriate row.
   * The appropriate row can be tracked using two variables: the current row and the current direction.
   * The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.
   * Time Complexity: O(n), where n == len(s)
   * Space Complexity: O(n)
   * @param s
   * @param numRows
   * @return
   */
  public String convert3(String s, int numRows) {

    if (numRows == 1) return s;

    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < Math.min(numRows, s.length()); i++)
      rows.add(new StringBuilder());

    int curRow = 0;
    boolean goingDown = false;

    for (char c : s.toCharArray()) {
      rows.get(curRow).append(c);
      if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
      curRow += goingDown ? 1 : -1;
    }

    StringBuilder ret = new StringBuilder();
    for (StringBuilder row : rows) ret.append(row);
    return ret.toString();
  }

  /**
   * official solution
   * Visit the characters in the same order as reading the Zig-Zag pattern line by line.
   *
   * For all whole numbers kk,
   *
   * Characters in row 0 are located at indexes k(2⋅numRows−2)
   * Characters in row numRows−1 are located at indexes k(2⋅numRows−2)+numRows−1
   * Characters in inner row ii are located at indexes k(2⋅numRows−2)+i and (k+1)(2⋅numRows−2)−i.
   * @param s
   * @param numRows
   * @return
   */
  public String convert4(String s, int numRows) {

    if (numRows == 1) return s;

    StringBuilder ret = new StringBuilder();
    int n = s.length();
    int cycleLen = 2 * numRows - 2;

    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j + i < n; j += cycleLen) {
        ret.append(s.charAt(j + i));
        if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
          ret.append(s.charAt(j + cycleLen - i));
      }
    }
    return ret.toString();
  }


  public static void main(String[] args) {
    P0006_M_ZigZagConvert instance = new P0006_M_ZigZagConvert();
    String result = instance.convert2("PAYPALISHIRING", 3);
    String result2 = instance.convert2("PAYPALISHIRING", 4);
    System.out.println(result);
    System.out.println(result2);
  }

}
