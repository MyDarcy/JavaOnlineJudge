package nowcoder.xiecheng;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {


  /*请完成下面这个函数，实现题目要求的功能
  当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
  ******************************开始写代码******************************/
  static int findMinMis(int[] A) {
    if (A == null) {
      return 1;
    }
    Arrays.sort(A);
    if (A.length == 1 && A[0] < 0) {
      return 1;
    } else if (A.length == 1 && A[0] > 1) {
      return 1;
    } else if (A.length == 1 && A[0] <= 1) {
      return A[0] + 1;
    } else if (A[A.length - 1] < 0) {
      return 1;
    }

    if (A[0] > 1) {
      return 1;
    }

    int prev = A[0];
    int current = 0;
    for (int i = 1; i < A.length; i++) {
      current = A[i];
      if (current < 0) {
        prev = current;
        continue;
      }

      if (prev < 0 && current > 1) {
        return 1;
      }

      if (prev >= 0 && current - prev >= 2) {
        return prev + 1;
      }

      prev = current;
    }
    if (prev <= 0) {
      return 1;
    } else if (prev == 1) {
      return 2;
    } else {
      return 1;
    }
  }
  /******************************结束写代码******************************/


  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int res;

    int _A_size = 0;
    _A_size = Integer.parseInt(in.nextLine().trim());
    int[] _A = new int[_A_size];
    int _A_item;
    for(int _A_i = 0; _A_i < _A_size; _A_i++) {
      _A_item = Integer.parseInt(in.nextLine().trim());
      _A[_A_i] = _A_item;
    }

    res = findMinMis(_A);
    System.out.println(String.valueOf(res));

  }
}
