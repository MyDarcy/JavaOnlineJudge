package jianzhioffer;

import java.util.regex.Pattern;

/**
 * Created by darcy
 * 2017/5/19--0:23
 * Description:
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class P54_NumbericString {

    /**
     * 正则表达式匹配;
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }

        String string = new String(str).toUpperCase();
        // 以数字, + , - 开头才继续运算;
        if (string.matches("[\\d]+.*") || string.startsWith("+") || string.startsWith("-")) {
            int indexE = string.indexOf('E');
            // 中间存在E;
            if (indexE != -1) {
                // 排除 +. -. 这样的情况。
                if (!string.substring(0, indexE).matches("[\\+\\-]?\\.")) {
                    if (string.substring(0, indexE).matches("[\\+\\-]?[\\d]*\\.?[\\d]*")) {

                        // E 后面仍然可以是 +10 -1这样的数字;
                        if (indexE == str.length || string.substring(indexE + 1).matches("[\\+\\-]?[\\d]+")) {
                            return true;
                        }
                    }
                }

                // 中间不存在E;
            } else {
                if (!string.substring(0, str.length).matches("[\\+\\-]?\\.")) {
                    if (string.substring(0, str.length).matches("[\\+\\-]?[\\d]*\\.?[\\d]*")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/6f8c901d091949a5837e24bb82a731f2
     * 这个是有问题的: +.
     */
    public boolean isNumeric2(char[] str) {
        String string = String.valueOf(str);
        return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
    }

    public static void main(String[] args) {
        P54_NumbericString demo = new P54_NumbericString();
        System.out.println(demo.isNumeric2("-100".toCharArray()));
    }


}
