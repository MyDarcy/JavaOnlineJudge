package com.darcy.main.review;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-9 下午1:57.
 * Description:
 */
public class MySort {

  /**
   * 选择排序.
   * @param array
   */
  public static void selectSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int min = array[i];
      int minIndex = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < min) {
          min = array[j];
          minIndex = j;
        }
      }
      if (minIndex != i) {
        array[minIndex] = array[i];
        array[i] = min;
      }
    }
  }

  /**
   * 插入排序.
   * @param array
   */
  public static void insertSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int temp = array[i];
      int j = i - 1;
      while (j >= 0 && array[j] > temp ) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = temp;
    }
  }

  /**
   * 冒泡排序.
   * @param array
   */
  public static void bubbleSort(int[] array) {
    // 总共n-1趟.
    for (int i = 0; i < array.length - 1; i++) {
      // 最后可能要交换的是 j和前面的元素i。
      for (int j = array.length - 1; j > i; j--) {
        if (array[j] < array[j - 1]) {
          swap(array, j, j - 1);
        }
      }
    }
  }

  private static void swap(int[] array, int j, int i) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void mergeSort(int[] array) {

  }

  public static void main(String[] args) {
    System.out.println("selectSort.");
    int[] array = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7};
    System.out.println(Arrays.toString(array));
    selectSort(array);
    System.out.println(Arrays.toString(array));

    System.out.println("----");
    System.out.println("insertSort.");
    int[] array2 = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7};
    System.out.println(Arrays.toString(array2));
    insertSort(array2);
    System.out.println(Arrays.toString(array2));

    System.out.println("----");
    System.out.println("bubbleSort.");
    int[] array3 = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7};
    System.out.println(Arrays.toString(array3));
    insertSort(array3);
    System.out.println(Arrays.toString(array3));

  }
}
