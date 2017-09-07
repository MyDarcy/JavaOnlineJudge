package jianzhioffer;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by darcy
 * 2017/4/10--0:43
 * Description:
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的
 * 前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相
 * 对位置不变。
 */
public class P14_OddEvenAdjustReplace {

  public void reOrderArray(int[] array) {

    if (array == null || array.length == 0) {
      return;
    }

    int first = 0;
    int end = array.length - 1;

    while (first <= end) {
      // 奇数;
      while ((array[first] & 1) == 1 && first <= end) {
        first++;
      }

      // 偶数;
      while ((array[end] & 1) == 0 && end >= first) {
        end--;
      }

      if (first <= end) {
        swap(array, first, end);
      }

    }
  }

  private void swap(int[] array, int first, int end) {
    int temp = array[first];
    array[first] = array[end];
    array[end] = temp;
  }

  public static void main(String[] args) {
    P14_OddEvenAdjustReplace demo = new P14_OddEvenAdjustReplace();

    int number = 20;
    int[] array = new int[number];

    Random random = new Random(31);
    for (int i = 0; i < number; i++) {
      array[i] = random.nextInt(31);
    }

    System.out.println(Arrays.toString(array));
    demo.reOrderArray(array);
    System.out.println(Arrays.toString(array));
  }

}
