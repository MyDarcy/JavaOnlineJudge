package eopi.ch17_dp;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-8-24 下午5:34.
 * Description:
 *
 * 给定最终的得分final, 而棒球选手单次可能的得分情况是一个数组array=[2, 3, 7](可以任意指定)
 * 那么有多少种组合情况得到最终的得分??????
 *
 * final=9, array=[2,3,7]
 *  - [7, 2]
 *  - [2, 7]
 *  - [6, 3] = [2, 2, 2, 3] + [3, 3, 3]
 * 实际上就只有3中可能, 存在顺序相反的重复情况.
 * f(n) = f(n - 7) + f(n - 3) + f(n -2); 但是不同的序列可能实际上上同一种组合(2 + 7)/(7 + 2).
 */
public class P3_Players {

  /**
   * f(n) = f(n - 7) + f(n - 3) + f(n -2); 但是不同的序列可能实际上上同一种组合(2 + 7)/(7 + 2).
   * 所以需要将不同的满足要求的结果序列排序，然后入set,得到最终的解决方案的个数.
   * 1. 数组的排序
   * 2. 含有相同元素个数以及顺序的序列入set如何重写equals()和hashCode()
   *
   * 例子：如果得分情况只有[2, 3],那么要得12分
   * 3 * 4
   *    2 + 3x (错误)
   *    2 * 2 + 3x (错误)
   * 2 * 3 + 3 * 2
   *    2 * 4 + 3x (错误)
   *    2 * 5 + 3x (错误)
   * 2 * 6
   * 如果得分情况是[2, 3, 7], 要得12分
   * 在上面的分析的基础之上，只有一种可能
   * 2 + 3 + 7
   *
   * ==> 重复的子问题计算, 所以时间复杂度是O(2^n)指数的时间复杂度.
   *
   * @return
   */
  public static int bruteForceSolution() {

    return 0;
  }

  /**
   * A[i][j]表示最终的得分为j, 而单次可能的得分是W[0], W[1], ..., W[i-1](从小到达的顺序排列);
   * A[1][12]就表示最终得分是12 (1从0开始计数), 得分数组是[2, 3]两个元素.
   * 那么:
   * A[i + 1][j] = 不包含W[i+1]得分的情况+包含一次得分W[i+1]+包含两次得分W[i+1]..直到得k次W[i+1]分总和已经超过j了.
   *
   * 那么计算方法1:包括三个循环, 总的时间复杂度O(arraySize * finalScore * arraySize)
   *  第一个循环是在总的得分范围上迭代
   *  第二个循环是在单次得分数组上迭代
   *  第三个循环是迭代最多 总得分/当前单次最高得分
   * 但是这种方法存在重复计算
   *  final=12, array=[2, 3], 需要计算的情况:A[0][0] + A[0][3] + A[0][6] + A[0][9] + A[0][12]　(和12的差额就用3分填充)
   *  final=15, array=[2, 3], 需要计算的情况:A[0][0] + A[0][3] + A[0][6] + A[0][9] + A[0][12] + A[0][15]
   *
   *  A[1][15] = A[1][12] + A[0][15]
   *
   *  A[1][0] = A[0][0],
   *  A[1][1] = A[0][1],
   *  A[1][2] = A[0][2],
   *  A[1][3] = A[0][3]+A[1][3-3],
   *  A[1][4] = A[0][4]+A[1][4-3],
   *  A[1][5] = A[0][5]+A[1][5-3],
   *
   *
   * 时间复杂度:O(arraySize * finalScore)
   * 空间复杂度:O(arraySize * finalScore)
   *
   * 方法1官方吐槽:
   * Let the 2D array A[i][j] store the number of score combinations
   * that result in a total of j, using individual plays of scores W[0], W[1],..., W[i-1], For
   * example, A[1][12] is the number of ways in which we can achieve a total of 12 points,
   * using 2 and/or 3 point plays. Then A[i+1][y] is simply A[i][j] (no W[i + 1] point plays
   * used to get to j),plus A[i][j- W[i +1]] (one W[i +1] point play), plus A[i][j- 2W[i +1]]
   * (two W[i + 1] point plays)
   *
   * A[i+1][j] = A[i][j] + A[i][j - W[i+1]] + A[i][j - 2W[i+1]] + ....
   *
   * @param finalScore
   * @param individualPlayScores
   * @return
   */
  public static int numberCombinationForFinalScore(
      int finalScore, List<Integer> individualPlayScores) {
    int[][] numCombinationForScore =
        new int[individualPlayScores.size()][finalScore + 1];

    for (int i = 0; i < individualPlayScores.size(); i++) {
      // 单次得分为individualPlayScores.get(i)这么多分, 同时最终得0分的情况只有一种，就是这种得分情况不计入(不得分)
      numCombinationForScore[i][0] = 1;
      for (int j = 1; j <= finalScore; j++) {

        // 不需要这次的得分individualPlayScores.get(i)也能获得最终的得分j;
        int withoutThePlay =
            i - 1 >= 0 ? numCombinationForScore[i - 1][j] : 0;

        // 需要本次的得分indivialPlayScores.get(i)才能获得最终的得分j;
        int withThePlay =
            j >= individualPlayScores.get(i) ?
                numCombinationForScore[i][j - individualPlayScores.get(i)] : 0;

        numCombinationForScore[i][j] = withoutThePlay + withThePlay;
      }
    }

    return numCombinationForScore[individualPlayScores.size() - 1][finalScore];
  }

  public static void main(String[] args) {
//    int finalScore = 9;
    int finalScore = 15;
    List<Integer> list = new ArrayList<>();
    list.add(2);
    list.add(3);
    list.add(7);
    System.out.println(numberCombinationForFinalScore(finalScore, list));

//    test();
  }

  /**
   * 不同长度的数组的排序.
   */
  private static void test() {
    List<int[]> list = new ArrayList<>();
    list.add(new int[]{7});
    list.add(new int[]{7, 1});
    list.add(new int[]{1, 7});
    list.add(new int[]{1,2, 7});
    list.add(new int[]{1,2, 7});
    list.add(new int[]{7, 2, 1});
    list.add(new int[]{7, 1, 1});
    list.add(new int[]{7, 1, 1, 0});
    list.add(new int[]{7, 3});
    list.add(new int[]{7, 3});
    list.sort(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        int minLength = o1.length < o2.length ? o1.length : o2.length;
        for (int i = 0; i < minLength; i++) {
          if (o1[i] < o2[i]) {
            return -1;
          } else if (o1[i] > o2[i]) {
            return 1;
          } else {
            continue;
          }
        }

        if (o1.length == o2.length) {
          return 0;
        } else if (o1.length > minLength) {
          return 1;
        } else {
          return -1;
        }

      }
    });

    Set<int[]> set = new HashSet<>();


    for (int i = 0; i < list.size(); i++) {
      set.add(list.get(i));
//      System.out.println(Arrays.toString(list.get(i)));
    }

    // 并没有得到去重复元素的目的.
    for (int[] item : set) {
      System.out.println(Arrays.toString(item));
    }
  }
}

/*
延伸:
Variant: Solve the same problem using 0(s) space.

Variant: Write a program that takes a final score and scores for individual plays, and
returns the number of sequences of plays that result in the final score. For example,
18 sequences of plays yield a score of 12. Some examples are (2, 2, 2, 3, 3), (2, 3, 2, 2, 3),
(2,3, 7), (7,3, 2)

Variant: Suppose the final score is given in the form (s,s'), i.ev Team 1 scored s points
and Team 2 scored s' points. How would you compute the number of distinct scoring
sequences which result in this score? For example, if the final score is (6,3) then
Team 1 scores 3, Team 2 scores 3, Team1 scores 3 is a scoring sequence which results
in this score.

Variant: Suppose the final score is (s,s'). How would you compute the maximum
number of times the team that lead could have changed? For example, if s = 10 and
s' = 6, the lead could have changed 4 times: Team 1 scores 2, then Team 2 scores 3
(lead change), then Team1scores 2 (lead change), then Team 2 scores3 (lead change),
then Team 1 scores 3 (lead change) followed by 3.


Variant: You are climbing stairs. You can advance 1 to k steps at a time. Your
destination is exactly n steps up. Write a program which takes as inputs n and k and
returns the number of ways in which you can get to your destination. For example,
if n = 4 and k = 2, there are five ways in which to get to the destination:
• four single stair advances,
• two single stair advances followed by a double stair advance,
• a single stair advance followed by a double stair advance followed by a single
stair advance,
• a double stair advance followed by two single stairs advances, and
• two double stair advances.
 */