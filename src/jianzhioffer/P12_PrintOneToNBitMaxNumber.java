package jianzhioffer;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/4/6--0:30
 * Description: 从1打印到N位的最大数;
 */
public class P12_PrintOneToNBitMaxNumber {
    public static void main(String[] args) {
        P12_PrintOneToNBitMaxNumber demo = new P12_PrintOneToNBitMaxNumber();

        // 打印从1到最大的N位数的问题;
        int nNumber = 5;
//        demo.printOneToN(nNumber);

        demo.printOneToNInCursive(nNumber);

    }

    public void printOneToNInCursive(int nNumber) {
        char[] nChars = new char[nNumber];
        Arrays.fill(nChars, '0');

        printInCursive(nChars, 0, nNumber);
    }

    private void printInCursive(char[] nChars, int level, int nNumber) {
        if (level > nNumber) {
            return;
        }
    }

    public void printOneToN(int n) {
        if (n <= 0) {
            throw new RuntimeException("invalid number");
        }

        char[] nChars = new char[n];
//        System.out.println(Arrays.toString(nChars));
        Arrays.fill(nChars, '0');
//        int i = 0;
        while (!increment(nChars)) {
            printNumber(nChars);
            /*if (i++ == 1000) {
                break;
            }*/
        }
    }


    /*
        字符串数组模拟加法运算;
     */
    private boolean increment(char[] nChars) {

        int currentSum = 0;
        int engateOne = 0;
        // isOverFlow代表最高位是否溢出了;
        boolean isOverFlow = false;
        for (int i = nChars.length - 1; i >= 0; i--) {
            currentSum = nChars[i] - '0' + engateOne;

            // 每一次都是从最末尾 +1;
            if (i == nChars.length - 1) {
                currentSum++;
            }

            if (currentSum >= 10) {

                if (i == 0) {
                    isOverFlow = true;
                } else {
                    currentSum = currentSum - 10;
                    engateOne = 1;
                    nChars[i] = (char) (currentSum + '0');
                }

            } else {
                nChars[i] = (char) (currentSum + '0');
                break;
            }
        }
        return isOverFlow;
    }

    private void printNumber(char[] nChars) {
        int i = 0;
        for (; i < nChars.length; i++) {
            if (nChars[i] != '0') {
                break;
            }
        }
        for (int j = i; j < nChars.length; j++) {
            System.out.print(nChars[j]);
        }
        System.out.println();
    }
}
