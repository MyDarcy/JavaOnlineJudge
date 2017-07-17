package ctci5th.chapter8.section5;

/**
 * Author by darcy
 * Date on 17-7-17 上午11:00.
 * Description:
 * 确定需要变换几个bit,才能将a变为b;
 */
public class P855_ChangeBitsFromAToB {

    /**
     * 思路: XOR的结果就表示不相等的位的结果.
     * @param a
     * @param b
     * @return
     */
    public static int bitsCount(int a, int b) {
        int count = 0;
        for (int i = a ^ b; i != 0; i >>= 1) {
            count += i & 1; // 值和最低位与的结果为1还是为0;
        }

        return count;
    }


    // C & (C - 1) == 0判断一个数是否是整数.

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static int bitsCount2(int a, int b) {
        int count = 0;
        int c = a ^ b;
        while (c != 0) {
            count++;
            // 消除最低位的1;
            c = c & (c - 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int a = Integer.parseInt("11001101", 2);
        int b = Integer.parseInt("10001001", 2);

        System.out.println(bitsCount(a, b));

        System.out.println(bitsCount2(a, b));
    }
}
