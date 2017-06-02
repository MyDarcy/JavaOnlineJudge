package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/29--0:45
 * Description:
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 n<=39
 */
public class P9_Fibnacci {
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if ( n == 1) {
            return 1;
        }

        int first = 0;
        int second = 1;
        int i = 2;
        int temp = 0;
        while (i <= n) {
            temp = first + second;
            first = second;
            second = temp;
            i++;
        }
        return second;
    }

    public static void main(String[] args) {
        P9_Fibnacci p9_fibnacci = new P9_Fibnacci();
        System.out.println(p9_fibnacci.Fibonacci(38));
    }
}
