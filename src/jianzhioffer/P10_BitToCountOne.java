package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/4--0:45
 * Description:
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class P10_BitToCountOne {
    public static void main(String[] args) {
        /*int i = 16;
        System.out.println(Integer.toHexString(i));*/
        /*
        Integer.bitCount();
         */
        int number = -1000;
        int oneBitCount = bitCount3(number);
        System.out.println(oneBitCount);

        System.out.println(Integer.bitCount(number));

    }

    private static int bitCount(int number) {
        int oneBitCount = 0;
        if (number >= 0) {
            int temp = number;
            while (temp != 0) {
                if ((temp & 1) == 1) {
                    oneBitCount++;
                }
                temp = temp >> 1;
            }
        } else {
            // <0 的情况不知道怎么写;
        }

        return oneBitCount;
    }

    private static int bitCount2(int number) {
        int count = 0;
        while (number != 0) {
            // 每次这样计算都可以消去一个1;
            number = number & (number - 1);
            count++;
        }
        return count;
    }

    // 正常思路, 通过右移动1 n次来判定整数中任意的特定bit是否为1;
    private static int bitCount3(int number) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((number & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }
}
