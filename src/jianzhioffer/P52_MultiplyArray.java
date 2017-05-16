package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/17--0:31
 * Description:
 *
 * 给定一个数组A[0,1,...,n-1],
 * 请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 */
public class P52_MultiplyArray {

    /**
     * 将前面或者后面的部分累积存起来，
     * 然后遍历一遍获取结果。
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length < 2) {
            return null;
        }
        int[] pre = new int[A.length]; // 多申请了一个位置；
        int[] rear = new int[A.length]; //　同上；

        for (int i = 0; i < A.length - 1; i++) {
            if (i == 0) {
                pre[i] = A[i];
            } else {
                pre[i] = A[i] * pre[i - 1];
            }
        }

        for (int i = A.length - 1; i > 0; i--) {
            if (i == A.length - 1) {
                rear[i] = A[i];
            } else {
                rear[i] = A[i] * rear[i + 1];
            }
        }

        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                result[i] = rear[1];
            } else if (i == A.length - 1) {
                result[i] = pre[A.length - 2];
            } else {
                result[i] = pre[i - 1] * rear[i + 1];
            }
        }

        return result;

    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        P52_MultiplyArray demo = new P52_MultiplyArray();
    }

}
