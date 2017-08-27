package eopi.ch16_recursion;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-27 上午10:15.
 * Description:
 *
 * 汗诺塔问题: A柱子上的牌子按照小盘在上，大盘在下的次序顺序排序.
 * 借助B柱子, 将所有的盘子移动到C柱子上,同样要保持A柱子上盘子的相对次序。
 *
 *
 * Write a program which prints a sequence of operations that transfers n rings from one
 * peg to another. You have a third peg, which is initially empty. The only operation
 * you can perform is taking a single ring from the top of one peg and placing it on the
 * top of another peg. You must never place a larger ring above a smaller ring.
 *
 * Hint: If you know how to transfer the top n — 1 rings, how does that help move the nth ring?
 *
 */
public class P2_Hanoi {

  /**
   *
   * @param n
   */
  public static void solution(int n) {
    solution(n, 'A', 'B', 'C');
  }

  /**
   * 将n个盘子从a柱借助b柱移动到c柱上.
   * @param n
   * @param a
   * @param b
   * @param c
   */
  private static void solution(int n, char a, char b, char c) {
    if (n == 1) {
      System.out.printf("%-3d %c -> %c\n", n, a, c);
      return;
    }

    // n-1个盘子从a借助c移动到b;
    solution(n - 1, a, c, b);
    // 盘子n从a到c.
    System.out.printf("%-3d %c -> %c\n", n, a, c);
    // n-1个盘子从b借助a移动到c.
    solution(n - 1, b, a, c);
  }


  /**
   * To transfer four rings, move the top three rings to the third peg, then
   * moving the lowest ring (which is the largest) to the second peg, and then transfer the
   * three rings on the third peg to the second peg, using the first peg as an intermediary.
   *
   *
   * The number of moves, T(n), satisfies the following recurrence: T(n) = T(n — 1) +1 +
   * T(n— 1) = 1 + 2T(n-1). The first T(n - 1) corresponds to the transfer of the top n — 1
   * rings from PI to P3, and the second T(n- 1) corresponds to the transfer from P3 to
   * P2. This recurrence solves to T(n) = 2" — 1. One way to see this is to "unwrap" the
   * so recurrence: T(n) = 1 + 2 + 4 + ... + 2^k T(n-k); Printing a single move take O(1) time
   * so time complexity O(2^n)
   *
   *
   * @param number
   */

  public static final int COLUMN_NUMBER = 3;

  public static void otherSolution(int number) {
    List<Deque<Integer>> columns = new ArrayList<>(COLUMN_NUMBER);
    for (int i = 0; i < COLUMN_NUMBER; i++) {
      columns.add(new LinkedList<>());
    }

    for (int i = number; i >= 1; i--) {
      columns.get(0).addFirst(i);
    }

    otherSolution(number, columns, 0, 1, 2);
  }

  private static void otherSolution(int number, List<Deque<Integer>> columns, int from, int to, int inter) {
    if (number > 0) {
      otherSolution(number - 1, columns, from, inter, to);
      columns.get(to).addFirst(columns.get(from).removeFirst());
      System.out.println("move " + from + " to " + to);
      otherSolution(number - 1, columns, inter, to, from);
    }
  }

  public static void main(String[] args) {
    solution(3);

    System.out.println();

    otherSolution(3);

  }

}

/*
Variant: Solve the same problem without using recursion.

Variant: Find the minimum number of operations subject to the constraint that each
operation must involve P3.

Variant: Find the minimum number of operations subject to the constraint that each
transfer must be from PI to P2, P2 to P3, or P3 to PI.

Variant: Find the minimum number of operations subject to the constraint that a ring
can never be transferred directly from PI to P2 (transfers from P2 to PI are allowed).

Variant: Find the minimum number of operations when the stacking constraint is
relaxed to the following—the largest ring on a peg must be the lowest ring on the
peg. (The remaining rings on the peg can be in any order, e.g., it is fine to have the
second-largest ring above the third-largest ring.)

Variant: You have In disks of n different sizes, two of each size. You cannot place a
larger disk on a smaller disk, but can place a disk of equal size on top of the other.
Compute the minimum number of moves to transfer the In disks from PI to P2.

Variant: You have 2n disks which are colored black or white. You cannot place a
white disk directly on top of a black disk. Compute the minimum number of moves
to transfer the 2n disks from PI to P2.

Variant: Find the minimum number of operations if you have a fourth peg P4
 */