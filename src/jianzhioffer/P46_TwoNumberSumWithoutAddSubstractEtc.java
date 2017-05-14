package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/15--0:16
 * Description:
 *
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 *
 */
public class P46_TwoNumberSumWithoutAddSubstractEtc {

    /**
     * 先计算二进制各位相加的值, 然后计算进位，最后这两个结果相加; 当然进位要一直计算下去;
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        int result = 0;
        int carry = 0;
        do {
            result = num1 ^ num2; // 计算各位的值;
            carry = (num1 & num2) << 1; // 计算进位;

            num1 = result;
            num2 = carry;
        } while (num2 != 0);

        return result;
    }

}
