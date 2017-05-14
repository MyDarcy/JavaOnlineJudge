package jianzhioffer;

import java.util.Arrays;

/**
 * Created by darcy
 * 2017/5/14--0:00
 * Description:
 * 扑克牌中随机抽取5张，判断是不是一个顺子，即5张
 * 牌是不是连续的，A为1， 2~10， J->11, Q->12, K->13; 大小王可以是任意数字用0表示 ;
 */
public class P44_Card {

    /**
     * 排序; 然后判断0的个数和两个不连续的数的差值的总和是否相等;
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        Arrays.sort(numbers);
        int zeroCnt = 0;
        int count = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                zeroCnt++;
            }
            if (numbers[i] != 0) {
                count += numbers[i + 1] - numbers[i] - 1;
            }
        }

        // [3,0,0,0,0]
        if (count == 0 && zeroCnt == 4) {
            return true;
        }

        if (zeroCnt == count) {
            return true;
        }
        return false;
    }

    /**
     * 还可以针对
     * @param args
     */

    public static void main(String[] args) {
        P44_Card demo = new P44_Card();
        int[] cards = {0, 1, 3, 4, 5};
        System.out.println(demo.isContinuous(cards));
    }

}
