package com.darcy.main.review;

import org.omg.CORBA.PERSIST_STORE;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Author by darcy
 * Date on 17-9-14 下午4:46.
 * Description:
 */
public class OtherProblems {

  /**
   * 消除嵌套表达式.
   * (1,(2,3),(4,(5,6),7))
   * @param str
   * @return
   */
  public static String removeInnerBracket(String str) {
    StringBuilder sb = new StringBuilder();
    char[] chars = str.toCharArray();
    int left = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '(') {
        left++;
        if (i == 0) {
          sb.append('(');
        }
      } else if (chars[i] == ')') {
        if (left > 0) {
          left--;
          if (i == chars.length - 1) {
            sb.append(")");
            break;
          }
        } else {
          System.out.println("Wrong Expression.");
          break;
        }
      } else if (chars[i] == ',' || (chars[i] >= '0' && chars[i] <= '9')) {
        sb.append(chars[i]);
      } else {
        System.out.println("Wrong Expression.");
        break;
      }
    }
    return sb.toString();
  }

  /**
   * 电话号码排序.
   *
   *
   *
   * @param array
   */
  int BIT_PER_WORDS = 32;
  int max = 99999999;
  int min = 10000000;
  int N = (max - min);

  int wordOffset(int b) {
    return b / BIT_PER_WORDS;
  }

  int bitOffSet(int b) {
    return b % BIT_PER_WORDS;
  }

  void setBit(int[] words, int b) {
    b -= min;
    words[wordOffset(b)] |= (1 << bitOffSet(b));
  }

  void clearBit(int[] words, int b) {
    /*b -= min;*/
    words[wordOffset(b)] &= ~(1 << bitOffSet(b));
  }

  boolean getBit(int[] words, int b) {
    /*b -= min;*/
    int bit = words[wordOffset(b)] & (1 << bitOffSet(b));
    return bit != 0;
  }

  /**
   * 排序随机的100个数字,每个数的范围(10000000, 99999999)
   * 大量的数而内存不够都可以采用这种做法.
   */
  public void phoneNumberSort() {

    Random random = new Random(31);
    int[] array = new int[100];
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(N) + min;
    }

    System.out.println(Arrays.toString(array));

    int[] words = new int[1 + N / BIT_PER_WORDS];
    for (int i = 0; i < array.length; i++) {
      setBit(words, array[i]);
    }

    int count = 0;
    for (int i = 0; i < N; i++) {
      if (getBit(words, i)) {
        System.out.print((i + min) + "\t");
        count++;
      }
    }
    System.out.println("\ncount:" + count);
  }

  public static void main(String[] args) {
    String str1 = "(1,(2,3),(4,(5,6),7))";
    /*String str2 = "(1,(2,3)),(4,(5,6),7))";*/
    System.out.println(removeInnerBracket(str1));
    System.out.println("----");
    /*System.out.println(removeInnerBracket(str2));*/

    OtherProblems demo = new OtherProblems();
    demo.phoneNumberSort();

    System.out.println("-----");
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((i1, i2) -> i1 - i2);
    // 自定义堆的大小。维持一个计数器即可.
    for (int i = 0; i < 100; i++) {
      priorityQueue.add(i * 10);
      if (priorityQueue.size() > 10) {
        priorityQueue.remove();
      }
    }

    for (Integer i : priorityQueue) {
      System.out.print(i + "\t");
    }
  }
}
