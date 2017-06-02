package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/29--0:52
 * Description:
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
 */
public class P9_SmogSteps {
    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }

        if (target == 2) {
            return 2;
        }

        int first = 1;
        int second = 2;
        int temp = 0;
        int i = 2;
        while (i <= target) {
            temp = first + second;
            first = second;
            second = temp;
            i++;
        }
        return second;
    }
}
