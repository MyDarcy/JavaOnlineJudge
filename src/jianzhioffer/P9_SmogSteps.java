package jianzhioffer;

/**
 * Created by darcy
 * 2017/3/29--0:52
 * Description:
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
