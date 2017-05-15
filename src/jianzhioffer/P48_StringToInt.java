package jianzhioffer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darcy
 * 2017/5/15--0:56
 * Description:
 *
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 *
 * 输入描述:
 输入一个字符串,包括数字字母符号,可以为空


 输出描述:
 如果是合法的数值表达则返回该数字，否则返回0
 */
public class P48_StringToInt {

    /**
     * 注意显示的正负号表示
     * @param str
     * @return
     */
    public int StrToInt(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        if (str.length() == 1 && (str.charAt(0) == '+' || str.charAt(0) == '-')) {
            return 0;
        }

        /*Pattern compile = Pattern.compile("\\d+");
        Matcher matcher = compile.matcher(str);
        boolean b = matcher.find();
        if (!b) {
            System.out.println("non digit.");
            return 0;
        }*/

        boolean matches = str.matches("[\\+\\-]?\\d+");
        if (!matches) {
//            System.out.println("non digit.");
            return 0;
        }

        int result = 0;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') {
            result = parseInt(str.substring(1));
            if (str.charAt(0) == '+') {
                return result;
            } else {
                return -result;
            }
        }

        result = parseInt(str);




        System.out.println(result);


        return result;
    }

    private int parseInt(String str) {
        int result = str.charAt(0) - '0';
        for (int i = 1; i < str.length(); i++) {
            result *= 10;
            result += str.charAt(i) - '0';
        }

        return result;
    }

    public static void main(String[] args) {
        P48_StringToInt demo = new P48_StringToInt();
        System.out.println(demo.StrToInt("+12"));;
    }

}
