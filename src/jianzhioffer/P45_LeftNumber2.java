package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/14--23:19
 * Description:
 * 0到n-1这n个数排成一个圆圈，从数字0开始从这个圆圈中删除第m个数，那么这个圆圈中剩下的最后一个数是哪一个;
 *
 *
 */
public class P45_LeftNumber2 {

    /**
     * 分析出迭代公式; 移除一个元素后从后一个元素开始，
     * 建立其跟0~n-2(现在只有n-1个元素)的映射关系, 以及这种反向的映射关系;
     *
     * f(n, m) = f(n - 1, m) if n > 1 else 0;
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    // 同一种思想的两种解法;
    public int LastRemaining_Solution2(int n, int m) {

        if (n < 1 || m < 1) {
            return -1;
        }

        if (n == 1) {
            return 0;
        }
        return (LastRemaining_Solution2(n - 1, m) + m )% n;
    }

    public static void main(String[] args) {
        P45_LeftNumber demo1 = new P45_LeftNumber();
        P45_LeftNumber2 demo2 = new P45_LeftNumber2();
        long start = System.currentTimeMillis();
        System.out.println(demo1.LastRemaining_Solution(4000, 997));
        long start2 = System.currentTimeMillis();
        System.out.println("TIME1:" + (start2 - start));
        System.out.println(demo2.LastRemaining_Solution(4000, 997));
        long start3 = System.currentTimeMillis();
        System.out.println("TIME2:" + (start3 - start2));
        System.out.println(demo2.LastRemaining_Solution2(4000, 997));
        long start4 = System.currentTimeMillis();
        System.out.println("TIME3:" + (start4 - start3));

    }

}
