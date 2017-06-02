package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/4--23:52
 * Description: 判断一个数是否是2的整数次幂;
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class P10_IsNumberTwoPower {
    public static void main(String[] args) {
        int[] numbers = {156678, 16384, 12, 128};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i] + " " + isTwoPower(numbers[i]));
        }
    }

    /*
       number & (number - 1)
     */
    private static boolean isTwoPower(int number) {
        return (number & (number - 1)) == 0 ? true: false;
    }
}
