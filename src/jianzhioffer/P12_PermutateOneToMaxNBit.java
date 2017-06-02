package jianzhioffer;

/**
 * Created by darcy
 * 2017/4/8--23:32
 * Description:
 * 1到最大的N位数。
 */
public class P12_PermutateOneToMaxNBit {
    private static int count;

    public static void main(String[] args) {
        P12_PermutateOneToMaxNBit demo = new P12_PermutateOneToMaxNBit();
        int number = 6;
        demo.permutate(number);
        System.out.println("count:" + count);

    }

    private void permutate(int number) {
        char[] chars = new char[number];
        for (int i = 0; i <= 9; i++) {
            chars[0] = (char) ('0' + i);
            printOneToMaxNBit(chars, number, 0);
        }
    }

    private void printOneToMaxNBit(char[] chars, int number, int i) {
        if (i == number - 1) {
            System.out.println(new String(chars));
            count++;
            return;
        }

        for (int j = 0; j <= 9; j++) {
            chars[i + 1] = (char) (j + '0');
            printOneToMaxNBit(chars, number, i + 1);
        }
    }
}
