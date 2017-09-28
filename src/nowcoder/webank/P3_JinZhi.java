package nowcoder.webank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by hzq19
 * Date on 2017/9/28 17:11.
 * Description:
 *
 * 给出一个非负整数 n，我们可以用各种进制来表示它。比如说数 23，它在十进制表示下就是 23，在二进制表示下是 10111，
 * 在八进制表示下是 27，在十二进制表示下 1B(B 表示 11)。 n 在某种进制表示下的权值为将其各位数字相加的和，比如 23
 * 在二进制表示下的权值为1+0+1+1+1=4，23 在八进制表示下的权值为 2+7=9，23 在十二进制表示下的权值为1+11=12。

 现在给出一个非负整数n，你可以用p进制去表示它(2 ≤p≤n )，同时你要使得它在这种进制表示下的权值最大。
 输入
 第一行包含一个整数n  , 2 ≤n ≤ 109
 输出
 输出最大的权值。

 样例输入
 23
 样例输出
 12

 Hint
 Input Sample
 57
 Output Sample
 29
 */
public class P3_JinZhi {

  static Map<Character, Integer> map = new HashMap<Character, Integer>() {
    {
      put('0', 0);
      put('1', 1);
      put('2', 2);
      put('3', 3);
      put('4', 4);
      put('5', 5);
      put('6', 6);
      put('7', 7);
      put('8', 8);
      put('9', 9);
      put('a', 10);
      put('b', 11);
      put('c', 12);
      put('d', 13);
      put('e', 14);
      put('f', 15);
    }
  };

  static Map<Integer, Character> numberToChar = new HashMap<Integer, Character>() {
    {
      put(0, '0');
      put(1, '1');
      put(2, '2');
      put(3, '3');
      put(4, '4');
      put(5, '5');
      put(6, '6');
      put(7, '7');
      put(8, '8');
      put(9, '9');
      put(10, 'a');
      put(11, 'b');
      put(12, 'c');
      put(13, 'd');
      put(14, 'e');
      put(15, 'f');

    }
  };

  public static void main(String[] args) {
    /*System.out.println(String.valueOf(Integer.MAX_VALUE).length());*/
    Scanner input = new Scanner(System.in);

    int number = input.nextInt();

    input.close();

/*    String s = toSize(number, 2);
    System.out.println(s);*/


    String str = null;
    int weight = 0;
    int max = Integer.MIN_VALUE;


    for (int size = 2; size <= number; size++) {
      int result = toSize2(number, size);
      if (result > max) {
        max = result;
      }
    }

    System.out.println(max);

    /*for (int size = 2; size <= 16; size++) {
      str = toSize(number, size);
      // if (size == 12) {
      //  System.out.println("size:" + str);
      //}
      weight =  weightString(str);
      if (weight > max) {
        max = weight;
      }
    }
    if ((number & 1) == 1) {
      max = Math.max(max, number / 2 + 1);
    }
    System.out.println(max);*/

    /*String hexString = Integer.toHexString(number);
    String octalString = Integer.toOctalString(number);


    int binaryCount = Integer.bitCount(number);
    int oct = oct(octalString);
    int tenCount = ten(String.valueOf(number));

    System.out.println();*/
  }

  private static int toSize2(int number, int size) {
    int result = 0;
    while (true) {
      int i = number % size;
      result += i;
      number = number / size;

      if (number == 0) {
        break;
      }
    }

    return result;
  }

  private static int weightString(String str) {
    int sum = 0;
    for (int i = 0; i < str.length(); i++) {
      sum += map.get(str.charAt(i));
    }
    return sum;
  }

  private static String toSize(int number, int size) {
    StringBuilder sb = new StringBuilder();
    while (true) {
      int i = number % size;

      sb.append(numberToChar.get(i));
      number = number / size;

      if (number == 0) {
        break;
      }
    }

    return sb.reverse().toString();
  }






  private static int ten(String s) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result += Character.getNumericValue(s.charAt(i));
    }
    return result;
  }

  private static int oct(String octalString) {
    int result = 0;
    for (int i = 0; i < octalString.length(); i++) {
      result += Character.getNumericValue(octalString.charAt(i));
    }
    return result;

  }
}
