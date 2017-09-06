package com.darcy.main.sorting;

import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-9-6 下午9:06.
 * Description:
 */
public class QuickSort {

  public static void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private static void quickSort(int[] array, int begin, int end) {
    if (begin < end) {
      int current = array[begin];
      int i = begin, j = end;
      while (i < j) {
        while (array[j] > current && i < j) {
          j--;
        }
        if (i < j) {
          array[i++] = array[j];
        }
        while (array[i] < current && i < j) {
          i++;
        }
        if (i < j) {
          array[j--] = array[i];
        }
      }
      array[i] = current;
      quickSort(array, begin, i - 1);
      quickSort(array, i + 1, end);
    }
  }

  public static void main(String[] args) {
    int[] array = {8, 2, 4, 8, 12, 7, 2, 8, 4, 2, 9, 5, 2, 11, 18};
    quickSort(array);
    System.out.println(Arrays.toString(array));
  }
}
