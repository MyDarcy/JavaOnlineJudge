package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/13--21:08
 * Description:
 *
 * 汇编语言中有一种移位指令叫做循环左移（ROL），
 * 现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class P42_RotateString {

    /**
     * 思路1: 把字符串拼接成两个，然后截断就可以了;
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        if (n > str.length()) {
            n = n % str.length();
        }

        int start = n;
        int length = str.length();
        str = str + str;
        return str.substring(start, start + length);
    }

    /**
     * 两次变换; 先变换前一部分，后变换后一部分。
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString2(String str,int n) {
        if (str == null || str.length() == 0) {
            return "";
        }

        if (n == 0) {
            return str;
        }

        if (n > str.length()) {
            n = n % str.length();
        }

        int firstStart = 0;
        int firstEnd = n;
        int secondStart = n;
        int secondEnd = str.length();
        return new StringBuilder(new StringBuilder(str.substring(firstStart, firstEnd))
                .reverse()
                .append(new StringBuilder(str.substring(secondStart, secondEnd)).reverse()))
                .reverse().toString();

    }

    public static void main(String[] args) {
        P42_RotateString p42_rotateString = new P42_RotateString();
        System.out.println(p42_rotateString.LeftRotateString("abcXYZdef", 3));
        System.out.println(p42_rotateString.LeftRotateString2("abcXYZdef", 3));
    }

}
