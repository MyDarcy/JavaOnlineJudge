package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/4--23:56
 * Description: 一个数的二进制表示进过多少次变换可以变成另外一个;
 * number & (number - 1)其实就是将number最低为1的位变为0;
 */
public class P10_TimesOfChange {

    public static void main(String[] args) {
        int source = 61134;
//        System.out.println(Integer.toBinaryString(source));

        int[] numbers = {123, 65535, 128, -100, 90};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(source + "\t" + numbers[i] + "\t" + timesOfChange(source, numbers[i]));
        }

    }

    /*
        异或运算后统计1个个数; 异或运算后为1的对应位不同，才需要改变;
     */
    private static int timesOfChange(int source, int number) {
        int i = source ^ number;
        return Integer.bitCount(i);
    }

}
