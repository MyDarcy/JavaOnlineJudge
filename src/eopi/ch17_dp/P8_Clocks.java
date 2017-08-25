package eopi.ch17_dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author by darcy
 * Date on 17-8-25 下午2:21.
 * Description:
 *
 * 背包问题
 * 一个袋子所能装的重量有限,每个clock都有一定的价值.
 * 如何使得袋子中装的clock的价值最大.
 *
 * Write a program for the knapsack problem that selects a subset of items that has
 * maximum value and satisfies the weight constraint. All items have integer weights
 * and values. Return the value of the subset.
 *
 * Hint: Greedy approaches are doomed.
 *
 */
public class P8_Clocks {

  private static class Item {
    Integer weight;
    Integer value;

    public Item(Integer weight, Integer value) {
      this.weight = weight;
      this.value = value;
    }
  }

  /**
   * 1. Greedy strategies such as picking the most valuable clock, or picking the clock
   * with maximum value-to-weight ratio(价值-重量比值), do not always give the optimum solution.
   *
   * 2. 遍历所有可能的子集, 计算满足约束下的最大值,那么时间复杂度是跟clock的个数成指数关系.
   *
   *  get the optimum solution by considering all subsets,
   *  This has exponential time complexity in the number of clocks.
   *
   *  关键就在于: brute-force enumeration is wasteful because it ignores the weight constraint.
   * 因为暴力破解算法忽略了约束条件.
   *
   * @param itemList
   * @param capacity
   * @return
   */
  public static int normalSolution(List<Item> itemList, int capacity) {


    return capacity;
  }

  /**
   * 如果选择A, 那么问题就变成 (capacity-Weight_a)和除A以外的子集选择最优解. totalValue += value_a;
   * 如果没有选择A, 那么文件就变成了 (capacity)和除A以外的子集中选择最优解, totaValue += 0;
   *
   * 构造一个最优解数组 V, V[i][w]表示: 可选的clock为0,1... i-1, 给定的capacity为w;
   * clock由0,1...i-1表示, 每个clock的重量和价值为wi和vi;
   *
   * 那么最优解可以使用如下的公式表示:
   *
   * V[i][w] = max{V[i - 1][w], V[i][w - wi] + vi} if wi <=w;
   *         = V[i - 1][w];   otherwise.
   *         V[i][w] = 0 (i == 0 || w == 0)
   *
   *
   *
   *
   * 官方吐槽:
   * The better approach is to simultaneously consider the weight constraint. For
   * example, what is the optimum solution if a given clock is chosen, and what is the
   * optimum solution if that clock is not chosen? Each of these can be solved **recursively**
   * with the implied weight constraint. For the given example, if we choose the Clock A,
   * we need to find the maximum value of clocks from Clocks B-P with a capacity of
   * 130- 20 and add $65 to that value. If we do not choose Clock A, we need to find the
   * maximum value of clocks from Clocks B-P with a capacity of 130. The larger of these
   * two values is the optimum solution
   *
   * More formally, let the clocks be numbered from 0 to n - 1, with the weight and the
   * value of the ith clock denoted by iv, and v,. Denote by V[i][w] the optimum solution
   * when we are restricted to Clocks 0,1, 2,..., i -1 and can carry w weight.
   *
   *  take i = 0 or w = 0 as bases cases—for these, V[i][w] = 0
   *
   *  The algorithm computes V[n - 1][w] in 0(nw) time, and uses O(nw) space
   *
   * @param capacity
   * @return
   */
  public static int dpSolution(List<Item> items, int capacity) {

    // V[i][j]代表从items[0...i]中选择clock, 重量约束为j;
    int[][] V = new int[items.size()][capacity + 1];
    for (int[] item : V) {
      Arrays.fill(item, -1);
    }

    return dpSolution(items, items.size() - 1, capacity, V);
  }

  /**
   * 从0...k中选择clock得到满足重量限制的最优解, 此时的重量约束是availableCapacity.
   *
   * @param items
   * @param k
   * @param availableCapacity
   * @param V
   * @return
   */
  private static int dpSolution(List<Item> items, int k, int availableCapacity, int[][] V) {
    if (k < 0) {
      return 0;
    }

    if (V[k][availableCapacity] == -1) {
      // 不选择第k个item.
      int withoutLastItem = dpSolution(items, k - 1, availableCapacity, V);

      // 根据可用重量确定能否选择第k个item,能的话就选择.
      int withLastItem
          = availableCapacity < items.get(k).weight
            ? 0
            : items.get(k).value
                + dpSolution(items, k - 1,
                availableCapacity - items.get(k).weight, V);

      V[k][availableCapacity] = Math.max(withoutLastItem, withLastItem);
    }

    return V[k][availableCapacity];
  }

  public static void main(String[] args) {
    Item item1 = new Item(5, 60);
    Item item2 = new Item(3, 50);
    Item item3 = new Item(4, 70);
    Item item4 = new Item(2, 30);
    List<Item> items = Arrays.asList(item1, item2, item3, item4);
    System.out.println(dpSolution(items, 6));
    System.out.println(dpSolution(items, 5));
    System.out.println(dpSolution(items, 4));
  }

}
/*
Variant: Solve the same problem using 0(w) space.

Variant: Solve the same problem using 0(C) space, where C is the number of
weights between 0 and w that can be achieved. For example, if the weights
are 100, 200, 200, 500, and w = 853, then C = 9, corresponding to the weights
0,100, 200,300, 400,500,600, 700,800.


Variant:Solve the fractional knapsack problem. In this formulation, the thief can take
a fractional part of an item, e.g., by breaking it. Assume the value of a fraction of an
item is that fraction times the value of the item.(部分背包问题)


Variant: In the "divide-the-spoils-fairly" problem, two thieves who have successfully
completed a burglary want to know how to divide the stolen items into two groups
such that the difference between the value of these two groups is minimized. For
example, they may have stolen the clocks in Figure 17.8 on Page 318, and would like
to divide the clocks between them so that the difference of the dollar value of the two
sets is minimized. For this instance, an optimum split is {A, G, J,M,O, P) to one thief
and the remaining to the other thief. The first set has value $1179, and the second has
value $1180. An equal split is impossible, since the sum of the values of all the clocks
is odd. Write a program to solve the divide-the-spoils-fairly problem.

Variant:Solve the divide-the-spoils-fairly problem with the additional constraint that
the thieves have the same number of items.

Variant: The US President is elected by the members of the Electoral College. The
number of electors perstateand Washington, D.C., are given in Table17.1. All electors
from each state as well as Washington, D.C., cast their vote for the same candidate.
Write a program to determine if a tie is possible in a presidential election with two
candidates. as follows:

State Electors State Electors State Electors
Alabama 9 Louisiana 8 Ohio 18
Alaska 3 Maine 4 Oklahoma 7
Arizona 11 Maryland 10 Oregon 7
Arkansas 6 Massachusetts 11 Pennsylvania 20
California 55 Michigan 16 Rhode Island 4
Colorado 9 Minnesota 10 South Carolina 9
Connecticut 7 Mississippi 6 South Dakota 3
Delaware 3 Missouri 10 Tennessee 11
Florida 29 Montana 3 Texas 38
Georgia 16 Nebraska 5 Utah 6
Hawaii 4 Nevada 6 Vermont 3
Idaho 4 New Hampshire 4 Virginia 13
Illinois 20 New Jersey 14 Washington 12
Indiana 11 New Mexico 5 West Virginia 5
Iowa 6 New York 29 Wisconsin 10
Kansas 6 North Carolina 15 Wyoming 3
Kentucky 8 North Dakota 3 Washington, D.C. 3
 */
