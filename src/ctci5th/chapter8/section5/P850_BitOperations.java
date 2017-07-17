package ctci5th.chapter8.section5;

/**
 * Author by darcy
 * Date on 17-7-17 上午8:50.
 * Description:
 */
public class P850_BitOperations {

    /**
     * 获取第i位的值是0还是1;
     * 思路: 1左移i位, 得到0001000值, 然后将这个数与num执行"位与"运算.
     * 就对i位以外的位执行了清0, 然后判断结果是否为0即可.
     * @param number
     * @param i
     * @return
     */
    public static boolean getBit(int number, int i) {
        return (number & (1 << i)) != 0;
    }


    /**
     * 设置第i位的值为1;]
     * 思路: 1左移i位, 得到0001000值, 然后将这个数与num执行"位或"运算.
     * 这样就只会改变第i位的值. 任意位跟0或操作都是原来的位.
     * @param number
     * @param i
     */
    public static int setBit(int number, int i) {
        return number | (1 << i);
    }

    /**
     * 第i位清0;
     * 思路: 1左移i位, 得到0001000值, 然后将这个数取反得到1110111, 与num执行"位与"运算.
     * 这样就只会清除第i位的值. 任意位跟1与操作都是原来的位.
     * @param number
     * @param i
     * @return
     */
    public static int clearBit(int number, int i) {
        int mask = ~(1 << i);
        return number & mask;
    }

    /**
     * 最高位到第i位都清0;
     * 思路: 1左移i位, 得到0001000值, 然后将这个数-1得到0000111, 与num执行"位与"运算.
     * 这样就只会清除最高位到第i位的值. 其余任意位跟1与操作都是原来的位.
     * @param number
     * @param i
     * @return
     */
    public static int clearHighBits(int number, int i) {
        return number & ((1 << i) - 1);
    }

    /**
     * i位到最低位都清0;
     * 思路: 1左移i+1位, 得到0010000值, 然后将这个数-1得到0001111, 再取反得到 1110000, 与num执行"位与"运算.
     * 这样就只会清除第i位到最低位的值. 其余任意位跟1与操作都是原来的位.
     * @param number
     * @param i
     * @return
     */
    public static int clearLowBits(int number, int i) {
        return number & ~((1 << (i + 1)) - 1);
    }

    /**
     * 第i位设置为v; 将清0和置位的操作结合起来.
     * 思路: 得到类似1110111的掩码, 与num执行"位与"运算.
     * 这样就只会清除第i位值. 然后将要设置的值v左移动i位, 得到0001000的值,然后执行位或运算.
     * @param number
     * @param i
     * @param v
     * @return
     */
    public static int updateBit(int number, int i, int v) {
        int mask = ~(1 << i);
        return (number & mask) | (v << i);
    }

    public static void main(String[] args) {
        int number = Integer.parseInt("111111000111", 2);
        System.out.println("Number:" + Integer.toBinaryString(number));
        boolean bool = getBit(number, 2);
        if (bool) {
            System.out.println("GetBit:" + 1);
        } else {
            System.out.println("GetBit:" + 0);
        }

        System.out.println("SetBit:" + Integer.toBinaryString(setBit(number, 3)));
        System.out.println("ClearBit:" + Integer.toBinaryString(clearBit(number, 6)));
        System.out.println("ClearHighBits:" + Integer.toBinaryString(clearHighBits(number, 10)));
        System.out.println("ClearLowBits:" + Integer.toBinaryString(clearLowBits(number, 2)));
    }
}
