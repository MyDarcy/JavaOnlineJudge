package eopi.ch6_arrays;


import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-8-28 下午3:17.
 * Description:
 *
 * 将数组中的数据按照 < pivot, = pivot, > pivot分成三个部分.从左到有排列.
 *
 */
public class P2_DutchNumber {

  /**
   * 三个list分别存 <pivot, =pivot, >pivot
   * 然后复制回原数组.
   *
   * O(n)的空间复杂度.
   * @param array
   */
  public static void bruteSolution(int[] array) {


  }

  /**
   *
   * 时间复杂度O(n^2)
   *
   * @param array
   * @param pivotIndex
   */
  public static void solution(int[] array, int pivotIndex) {
    int pivot = array[pivotIndex];
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        // 每次都将 < pivot的元素移动到前面.
        if (array[j] < pivot) {
          swap(array, i, j);
          break;
        }
      }
    }

    for (int i = array.length - 1; i >= 0 && array[i] >= pivot; i--) {
      for (int j = i - 1; j >= 0 && array[j] >= pivot; j--) {
        // 将大于pivot的元素移动到后面.
        if (array[j] > pivot) {
          swap(array, i, j);
          break;
        }
      }
    }
  }

  public static void betterSolution(int[] array, int pivotIndex) {
    int pivot = array[pivotIndex];
    int smallerIndex = 0;
    // 只能把小的元素移动到前面.
    for (int i = 0; i < array.length; i++) {
      if (array[i] < pivot) {
        swap(array, smallerIndex++, i);
      }
    }

    System.out.println(Arrays.toString(array));

    int biggerIndex = array.length - 1;
    for (int i = array.length - 1; i >= 0 /*&& array[i] >= pivot*/; i--) {
      if (array[i] > pivot) {
        swap(array, biggerIndex--, i);
      }
    }
  }

  public static void otherSolution(int[] array, int pivotIndex) {
    int pivot = array[pivotIndex];
    int smaller = 0;
    int equal = 0;
    int bigger = array.length;
    while (equal < bigger) {
      // samller到equal之间的元素是是 <= equal处的值的那部分元素.
      if (array[equal] < pivot) {
        swap(array, smaller++, equal++);
      } else if (array[equal] == pivot) {
        equal++;
      } else {
        swap(array, equal, --bigger);
      }
    }
  }

  private static void swap(int[] ints, int i, int j)
  {
    int temp = ints[i];
    ints[i] = ints[j];
    ints[j] = temp;
  }

  public static void main(String[] args) {
    int arraySize = 12;
    int[] array = new int[arraySize];
    Random random = new Random(31);
    for (int i = 0; i < arraySize; i++) {
      array[i] = random.nextInt(3);
    }
    System.out.println(Arrays.toString(array));
    solution(array, 5);
    System.out.println(Arrays.toString(array));

    for(int i = 0; i <arraySize; i++) {
      int i1 = random.nextInt(arraySize);
      int i2 = random.nextInt(arraySize);
      swap(array, i1, i2);
    }
    System.out.println(Arrays.toString(array));
    betterSolution(array, 6);
    System.out.println(Arrays.toString(array));
  }



}
