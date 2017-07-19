package ctci5th.chapter8.section7;

/**
 * Author by darcy
 * Date on 17-7-19 下午8:37.
 * Description:
 * 整数的除法,减法,乘法,只允许+来实现.
 */
public class P874_DivMulSubWithPlusImpls {

    /**
     * 求一个数的负数
     * @param a
     * @return
     */
    public static int negate(int a) {
        int result = 0;
        int inc = a < 0 ? 1 : -1;
        while (a != 0) {
            a += inc;
            result += inc;
        }
        return result;
    }

    /**
     * 求b的负数
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return a + negate(b);
    }

    /**
     * 迭代少一点次数
     * @param a
     * @param b
     * @return
     */
    public static int multiply(int a, int b) {
        if (a < b) {
            return multiply(b, a);
        }
        int sum = 0;
        for (int i = 0; i < Math.abs(b); i++) {
            sum += a;
        }
        if (b < 0) {
            sum = negate(sum);
        }
        return sum;

    }

    /**
     * 求绝对值
     * @param a
     * @return
     */
    public static int abs(int a) {
        if (a < 0) {
            return negate(a);
        } else {
            return a;
        }

    }


    /**
     * 只需要求b + ... +b (x个) <= a即可求解.
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("divided with 0");
        }

        int absa = abs(a);
        int absb = abs(b);

        int result = 0;
        int temp = 0;
        while (temp + absb <= absa) {
            temp += absb;
            result++;
        }

        if ((a > 0 && b > 0) || (a < 0 && b < 0)) {
            return result;
        } else {
            return negate(result);
        }

    }

}
