package nowcoder.pingduoduo;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by hzq19 on 2017/8/1.
 * 给定的大整数相乘。输入用字符串表示，输出结果也用字符串表示。
 */
public class BigIntegerStringMultiply {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] bigString = input.nextLine().trim().split("\\s+");

        String str1 = bigString[0];
        String str2 = bigString[1];

        String[] results = new String[str2.length()];
        for (int i = str2.length() - 1, j = 0; i >= 0; i--, j++) {
            results[j] = multiply(str1, str2.charAt(i)) + fill(j);
        }

        /*for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }*/

        int length = results[results.length - 1].length();
        for (int i = 0; i < results.length; i++) {
            results[i] = fill(length - results[i].length()) + results[i];
        }


       /* for (int i = 0; i < results.length; i++) {
            System.out.println(results[i]);
        }*/

        int increment = 0;
        StringBuilder sb = new StringBuilder();


        // 从末位往前遍历。
        for (int i = length - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = 0; j < results.length; j++) {
                sum += (results[j].charAt(i) - '0');
            }
            sum += increment;
           /* System.out.println(sum);*/
            if (i == 0) {
                sb.insert(0, sum);
                break;
            }
            increment = sum / 10;
            sb.insert(0, sum % 10);
        }

        System.out.println(sb.toString());





        /*String multiply = multiply("1234", '5');
        System.out.println(multiply);*/

    }

    private static String fill(int j) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < j; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    private static String multiply(String str1, char c) {
        // 返回一个长度为str1.length(), 值全为0的字符串。
        if (c == '0') {
            char[] chars = new char[str1.length()];
            Arrays.fill(chars, '0');
            return new String(chars);
        }

        StringBuilder sb = new StringBuilder();
        int increment = 0;
        int sum = 0;
        int charValue = c - '0';
        for (int i = str1.length() - 1; i >= 0; i--) {
            sum = (str1.charAt(i) - '0') * charValue + increment;
            if (i == 0) {
                sb.insert(0, sum);
                break;
            }
            increment = sum / 10; // 进位;
            sb.insert(0, sum % 10); // 余数
        }
        return sb.toString();
    }



}
