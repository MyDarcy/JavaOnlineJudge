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
   *
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
   *
   * @param array
   */
  public static void insertSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int temp = array[i];
      int j = i - 1;
      while (j >= 0 && array[j] > temp) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = temp;
    }
  }

  /**
   * 冒泡排序.
   *
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

  /**
   * 自顶向下归并.
   *
   * @param array
   */
  public static void mergeSort(int[] array) {
    // 初始化aux数组.

    mergeSort(array, 0, array.length - 1);

  }

  private static void mergeSort(int[] array, int start, int end) {
    if (start < end) {
      int middle = (start + end) / 2;
      mergeSort(array, start, middle);
      mergeSort(array, middle + 1, end);
      mergeSort(array, start, middle, end);
    }
  }

  private static void mergeSort(int[] array, int start, int middle, int end) {
    int[] left = new int[middle - start + 1];
    int[] right = new int[end - middle];
    for (int i = 0, j = start; /*i < middle - start + 1*/ j <= middle; i++, j++) {
      left[i] = array[j];
    }

    for (int i = 0, j = middle + 1; /*i < end - middle*/ j <= end; i++, j++) {
      right[i] = array[j];
    }
    int i = 0, j = 0, k = start;
    for (/*int i = 0, j = 0, k = start*/; i < middle - start + 1 && j < end - middle; k++) {
      if (left[i] < right[j]) {
        array[k] = left[i++];
      } else {
        array[k] = right[j++];
      }
    }

    if (i < middle - start + 1) {
      for (int l = i; l < middle - start + 1; l++, k++) {
        array[k] = left[l];
      }
    }

    if (j < end - middle) {
      for (int l = j; l < end - middle; l++, k++) {
        array[k] = right[l];
      }
    }
  }


  private static int[] aux;
  public static void mergeSort2(int[] array) {
    aux = new int[array.length];
    mergeSort2(array, 0, array.length - 1);

  }

  private static void mergeSort2(int[] array, int start, int end) {
    if (end <= start) {
      return;
    }

    int middle = (start + end) / 2;
    mergeSort2(array, start, middle);
    mergeSort2(array, middle + 1, end);
    mergeSort2(array, start, middle, end);

  }

  private static void mergeSort2(int[] array, int start, int middle, int end) {
    int i = start;
    int j = middle + 1;
    for (int k = start; k <= end; k++) {
      aux[k] = array[k];
    }

    for (int k = start; k <= end; k++) {
      if (i > middle) {
        array[k] = aux[j++];
      } else if (j > end) {
        array[k] = aux[i++];
      } else if (aux[j] < aux[i]) {
        array[k] = aux[j++];
      } else {
        array[k] = array[i++];
      }
    }
  }

  /**
   * 快速排序.
   *
   * @param array
   */
  public static void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  private static void quickSort(int[] array, int startIndex, int endIndex) {
    // 范围.
    if (startIndex < endIndex) {
      // 现在start处就是冗余的元素了.
      int start = startIndex;
      int end = endIndex;
      int pivot = array[start];
      while (start < end) {
        while (array[end] > pivot && start < end) {
          end--;
        }
        // 现在end处就是冗余的元素.
        if (start < end) {
          array[start++] = array[end];
        }

        while (array[start] < pivot && start < end) {
          start++;
        }

        // 现在start处就是冗余的元素.
        // 再循环就是重复这个过程.
        if (start < end) {
          array[end--] = array[start];
        }
      }
      array[start] = pivot;
      quickSort(array, startIndex, start - 1);
      quickSort(array, start + 1, endIndex);
    }
  }

  /**
   * Shell排序.
   * @param array
   */
  public static void shellSort(int[] array) {
    for (int h = array.length / 2; h >= 1; h /= 2) {
      // 插入排序的变种.
      // 将元素往前移动到current - skip处.
      for (int i = h; i < array.length; i++) {
        int temp = array[i];
        int j = i;
        // 大的元素往后移动,
        for (; j >= h; j -= h) {
          // 给temp腾位置.
          if (array[j - h] > temp) {
            array[j] = array[j - h];
          } else {
            break;
          }
        }
        // temp插入到合适的位置.
        array[j] = temp;
      }
    }

    // also ok...
    /*int N = array.length;
    int h = 1;
    while (h < N / 3) {
      h = h * 3 + 1;
    }

    while (h >= 1) {
      for (int i = h; i < N; i++) {
        for (int j = i; j >= h && array[j - h] > array[j]; j -= h) {
          swap(array, j, j - h);
        }
      }
      h /= 3;
    }*/
  }


  /**
   * 堆排序.
   * @param arr
   */
  private static void heapSort(int[] arr) {
    // 将待排序的序列构建成一个大顶堆
    for (int i = arr.length / 2; i >= 0; i--){
      heapAdjust(arr, i, arr.length);
    }

    // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
    for (int i = arr.length - 1; i > 0; i--) {
      swap(arr, 0, i); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
      heapAdjust(arr, 0, i); // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
    }
  }

  /**
   * 构建堆的过程
   * @param arr 需要排序的数组
   * @param i 需要构建堆的根节点的序号
   * @param n 数组的长度
   */
  private static void heapAdjust(int[] arr, int i, int n) {
    int child;
    int father;
    for (father = arr[i]; leftChild(i) < n; i = child) {
      child = leftChild(i);

      // 如果左子树小于右子树，则需要比较右子树和父节点
      if (child != n - 1 && arr[child] < arr[child + 1]) {
        child++; // 序号增1，指向右子树
      }

      // 如果父节点小于孩子结点，则需要交换
      if (father < arr[child]) {
        arr[i] = arr[child];
      } else {
        break; // 大顶堆结构未被破坏，不需要调整
      }
    }
    arr[i] = father;
  }

  // 获取到左孩子结点
  private static int leftChild(int i) {
    return 2 * i + 1;
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
    bubbleSort(array3);
    System.out.println(Arrays.toString(array3));

    System.out.println("------");
    System.out.println("MergeSort");
    int[] array4 = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7};
    System.out.println(Arrays.toString(array4));
    mergeSort(array4);
    System.out.println(Arrays.toString(array4));

    System.out.println("-----");
    System.out.println("MergeSort2");
    int[] array42 = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7, 20, 12, 4, 8, 3, 14, 25};
    System.out.println(Arrays.toString(array42));
    mergeSort2(array42);
    System.out.println(Arrays.toString(array42));

    System.out.println("-----");
    System.out.println("QuickSort");
    int[] array5 = new int[]{4, 5, 1, 4, 3, 9, 0, 6, 11, 7, 20, 12, 4, 8, 3, 14, 25};
    System.out.println(Arrays.toString(array5));
    quickSort(array5);
    System.out.println(Arrays.toString(array5));

    System.out.println("-----");
    System.out.println("shellSort");
    int[] array6 = new int[]{4, 5, 1, 4, 3, 9, 1, 6, 11, 7, 20, 12, 4, 8, 3, 14, 25};
    System.out.println(Arrays.toString(array6));
    shellSort(array6);
    System.out.println(Arrays.toString(array6));

    System.out.println("-----");
    System.out.println("HeapSort");
    int[] array7 = new int[]{4, 5, 1, 4, 3, 9, 1, 6, 11, 7, 20, 12, 4, 8, 3, 14, 25};
    System.out.println(Arrays.toString(array7));
    heapSort(array7);
    System.out.println(Arrays.toString(array7));
  }


}
