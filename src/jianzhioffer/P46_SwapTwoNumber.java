package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/15--0:38
 * Description:
 * 不使用新变量交换两个值；
 */
public class P46_SwapTwoNumber {

    /**
     * 加法;
     * @param number1
     * @param number2
     */
    public void solution(int number1, int number2) {
        System.out.println(number1 + "\t" + number2);
        number2 = number1 + number2;
        number1 = number2 - number1;
        number2 = number2 - number1;
        System.out.println(number1 + "\t" + number2);

    }

    /**
     * 异或运算;
     * @param number1
     * @param number2
     */
    public void solution2(int number1, int number2) {
        System.out.println(number1 + "\t" + number2);
        number2 = number1 ^ number2;
        number1 = number2 ^ number1;
        number2 = number2 ^ number1;
        System.out.println(number1 + "\t" + number2);
    }

    public static void main(String[] args) {
        P46_SwapTwoNumber demo = new P46_SwapTwoNumber();
        demo.solution(1, 100);
        System.out.println();
        demo.solution2(100, 200);
    }


}
