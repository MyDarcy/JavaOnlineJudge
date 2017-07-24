package ctci5th.chapter8.section9;

/**
 * Author by darcy
 * Date on 17-7-24 下午4:01.
 * Description:
 * 每次跳楼梯可以跳一级,两级,三级.
 *
 * 思路: 子问题求和
 */
public class P901_Steps {

    public static int solution1(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return solution1(n - 1) + solution1(n - 2) + solution1(n - 3);
        }
    }

    /**
     * 避免重复子问题运算，所以需要缓存结果值.
     * 动态规划求值.
     * @param n
     * @param map
     * @return
     */
    public static int solution2(int n, int[] map) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (map[n] > 0) {
            return map[n];
        } else {
            map[n] = solution2(n - 1, map)
                    + solution2(n - 2, map)
                    + solution2(n - 3, map);
        }
        return map[n];
    }

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        int kth = 34;
        int number1 = solution1(kth);
        long time1Off = System.currentTimeMillis() - time1;
        System.out.println(number1 + "\ttime:" + time1Off);

        long time2 = System.currentTimeMillis();
        int[] map = new int[kth + 1];
        int number2 = solution2(kth, map);
        long time2Off = System.currentTimeMillis() - time2;
        System.out.println(number2 + "\ttime:" + time2Off);
    }

}
