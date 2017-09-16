package com.darcy.main.review.heap;

import java.util.Arrays;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-16 上午10:08.
 * Description:
 */

public class HeapSort2 {
  private HeapSort2() {
  }

  public static void sort(Comparable[] pq) {
    int n = pq.length;

    for(int k = n / 2; k >= 1; --k) {
      sink(pq, k, n);
    }

    while(n > 1) {
      exch(pq, 1, n--);
      sink(pq, 1, n);
    }

  }

  private static void sink(Comparable[] pq, int k, int n) {
    while(true) {
      if(2 * k <= n) {
        int j = 2 * k;
        if(j < n && less(pq, j, j + 1)) {
          ++j;
        }

        if(less(pq, k, j)) {
          exch(pq, k, j);
          k = j;
          continue;
        }
      }

      return;
    }
  }

  private static boolean less(Comparable[] pq, int i, int j) {
    return pq[i - 1].compareTo(pq[j - 1]) < 0;
  }

  private static void exch(Object[] pq, int i, int j) {
    Object swap = pq[i - 1];
    pq[i - 1] = pq[j - 1];
    pq[j - 1] = swap;
  }

  private static void show(Comparable[] a) {
    for(int i = 0; i < a.length; ++i) {
      System.out.print(a[i] + "\t");
    }

  }

  private static void test() {
    int times = 5;
    int base = 10;
    Random random = new Random(31);
    for (int i = 0; i < 5; i++) {
      Integer[] pq = new Integer[base * (int) Math.pow(10, i)];
      int max = base * (int) Math.pow(10, i + 1);
      for (int j = 0; j < pq.length; j++) {
        pq[j] = random.nextInt(max);
      }

      sort(pq);
      boolean result = check(pq);
      if (result) {
        System.out.println("right.");
      } else {
        System.out.println("wrong.");
      }
    }


  }

  private static boolean check(int[] integers) {
    for (int i = 0; i < integers.length - 1; i++) {
      if (integers[i] > integers[i + 1]) {
        return false;
      }
    }
    return true;
  }


  private static boolean check(Integer[] integers) {
    for (int i = 0; i < integers.length - 1; i++) {
      if (integers[i] > integers[i + 1]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int arrayNumber = 15;
    Integer[] integers = new Integer[arrayNumber];
    Random random = new Random(31);
    for (int i = 0; i < integers.length; i++) {
      integers[i] = random.nextInt(100);
    }
    show(integers);
    sort(integers);
    System.out.println("\n--after sort--\n");
    show(integers);


    System.out.println("\n");
    test();

  }
}
