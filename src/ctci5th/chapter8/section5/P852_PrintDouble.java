package ctci5th.chapter8.section5;

import java.awt.*;

/**
 * Author by darcy
 * Date on 17-7-17 上午9:57.
 * Description:
 * 给定一个0到1之间的实数, 类型为double, 打印它的二进制表示,
 * 如果该数字无法用32位以内的二进制表示,则打印Error.
 */
public class P852_PrintDouble {

    /**
     * 将这个数乘以2,检查是否大于1; 是的话,那么小数点后第一位是是二进制1;  跟整数的二进制操作其实差不多.
     *
     * 实际上调试的时候发现其实double最低几位30,31位都是带有数据的, 就是double的不精确表示导致的.
     * @param number
     */
    public static String printBinary(double number) {
        if (number <= 0 || number >= 1) {
            return "Error";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(".");

        while (number > 0) {
            if (sb.length() >= 32) {
                return "Error";
            }

            double mul = number * 2;
            if (mul >= 1) {
                sb.append(1);
                number = mul - 1;
            } else {
                sb.append(0);
                number = mul;
            }
        }

        return sb.toString();

    }

    /**
     * 跟0.5, 0.25, 0.125等做比较.
     * @param number
     * @return
     */
    public static String printBinary2(double number) {
        if (number >= 1 || number <= 0) {
            return "Error";
        }

        double fraction = 0.5;
        StringBuilder sb = new StringBuilder();
        sb.append(".");
        while (number > 0) {
            if (sb.length() >= 32) {
                return "Error";
            }

            if (number >= fraction) {
                sb.append(1);
                number -= fraction;
            } else {
                sb.append(0);
            }

            fraction /= 2;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Double d = 0.00333999333333111111;
        String s = printBinary(d);
        System.out.println(s);

        double d2 = 0.5050050;
        String s2 = printBinary(d2);
        System.out.println(s2);
    }

}
