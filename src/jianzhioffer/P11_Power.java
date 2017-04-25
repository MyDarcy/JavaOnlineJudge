package jianzhioffer;

import java.util.Scanner;

/**
 * Created by darcy
 * 2017/4/5--23:40
 * Description: 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方;
 */
public class P11_Power {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double base = input.nextDouble();
        int exponent = input.nextInt();

        double value = Power(base, exponent);
        double value3 = new P11_Power().Power3(base, exponent);
        System.out.println(value + "\t" + value3);
    }

    /*
        普通版本;
     */
    public static double Power(double base, int exponent) {
        double value = 1.0;
        if (exponent > 0) {
            while (exponent-- > 0) {
                value *= base;
            }
        } else {
            exponent = Math.abs(exponent);
            while (exponent-- > 0) {
                value *= base;
            }
            value = 1.0 / value;
        }

        return value;
    }

    /*
        a. 考虑base为0并且exponent < 0的情况;
     */
    public double power(double base, int exponent) {
        if (equal(base, 0.0) && exponent < 0) {
            throw new RuntimeException("arguemnt invalid, base 0 and minus number");
        }

        double value = getPower(base, exponent);
        if (exponent < 0) {
            value = 1.0 / value;
        }
        return value;
    }

    /*
     a. 使用指数的平方;
     b. 位运算来代替求余数运算判断一个数是否是奇数;
     */

    public double Power3(double base, int exponent) {
        if (exponent == 1) {
            return base;
        }
        if (exponent == 0) {
            return 1.0;
        }

        double value = Power3(base, exponent >> 1);
        value *= value;
        if ((exponent & 0x1) == 1) {
            value *= base;
        }
        return value;
    }

    private double getPower(double base, int expoent) {
        double value = 1.0;
        expoent = Math.abs(expoent);
        while (expoent-- > 0) {
            value *= base;
        }
        return value;
    }

    private boolean equal(double base, double second) {
        if ((base - second) < 1E-6 && (base - second) > -1E-6) {
            return true;
        }
        return false;
    }
}
