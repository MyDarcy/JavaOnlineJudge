package ctci5th.chapter8.section5;

/**
 * Author by darcy
 * Date on 17-7-17 上午11:15.
 * Description:
 * 使用最少的命令来交换某个整数的奇bit和偶bit,
 * 即bit0和bit1交换,bit2与和bit3交换等.
 */
public class P856_SwapOvenUnOvenBitsWithMinOps {

    /**
     * 思路: 提取奇数位, 提取偶数位, OR
     * @param a
     * @return
     */
    public static int swapBits(int a) {
        int unoven = (0x55555555) & a; // 提取奇数位
        int oven = (0xaaaaaaaa) & a; // 提取偶数位

        // 奇数位右移, 偶数位左移
        return (oven >> 1) | (unoven << 1);
    }

}
