package ctci5th.chapter8.section5;

/**
 * Author by darcy
 * Date on 17-7-17 上午9:35.
 * Description:
 * 两个32位的整数N, M, 以及表示比特位置的i和j, 编写一个方法,将M插入到N中.
 * 使得M从j位开始,到第i位结束.而第j到第i位足够容纳M.
 *
 * 例子: N = 10000000000, M = 10011, i = 2, j = 6;
 * 输出N  =  10001001100
 *
 */
public class P851_UpdateFromIToJBits {

    /**
     * 思路: 将n从第i位到第j位置位, 然后将m左移动i位,或运算.
     * @param n
     * @param m
     * @param i
     * @param j
     * @return
     */
    int updateBits(int n, int m, int i, int j) {
        int allOnes = ~0;
        // 位置j之前的位都置为1,
        int left = allOnes << (j + 1);

        // 位置i之后的位置都置为1:
        int right = (1 << i) - 1;

        // 除第i位到第j为都置为1;
        int mask = left | right;

        // 对n的第i到第j位进行置位.
        int cleared = n & mask;
        // m左移动j位
        int shifted = m << i;

        // 两个有数字部分都不重叠的数字进行或运算.
        return shifted | cleared;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt("10000000000", 2);
        int m = Integer.parseInt("10011", 2);
        int i = 2;
        int j = 6;
        System.out.println(Integer.toBinaryString(n));
        P851_UpdateFromIToJBits demo = new P851_UpdateFromIToJBits();
        System.out.println(Integer.toBinaryString(demo.updateBits(n, m, i, j)));
    }
}
