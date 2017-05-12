package jianzhioffer;

/**
 * Created by darcy
 * 2017/5/11--0:48
 * Description:
 *
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class P40_NumberOccurenceOnceInArray {

    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array == null || num1 == null || num2 == null) {
            return;
        }

        if (array.length == 0 || num1.length == 0 || num2.length == 0) {
            return;
        }

        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result ^= array[i];
        }

        // 找出字符串中最后一个1的位置;
        String string = Integer.toBinaryString(result);
        int index = string.length() - string.lastIndexOf('1') - 1;

        // &的方法找出1的位置;
        /*int index = firstBitOne(result);*/

        System.out.println(result + "\t" + index);




        num1[0] = 0;
        num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
            // 特定的位是否是1;
            if (isBitOne(array[i], index)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    private int firstBitOne(int result) {
        int index = 0;
        while ((result & 1) == 0) {
            index++;
            result >>= 1;
        }
        return index;
    }

    private boolean isBitOne(int number, int offset) {
        return ((number >> offset) & 1) != 0;
    }

    public static void main(String[] args) {
        int[] array = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        P40_NumberOccurenceOnceInArray demo = new P40_NumberOccurenceOnceInArray();
        demo.FindNumsAppearOnce(array, num1, num2);

        System.out.println(num1[0] + "\t" + num2[0]);
    }

}

/**
 *
 测试用例:
 [2,4,3,6,3,2,5,5]
 "4,6"
 */
