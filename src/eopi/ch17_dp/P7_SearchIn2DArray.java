package eopi.ch17_dp;

import java.util.*;

/**
 * Author by darcy
 * Date on 17-8-25 上午10:10.
 * Description:
 * 给定一个二维数组以及一个一维模式数组，
 * 能否从某一个节点开始, 遍历相邻元素(left, right, down, above)使得模式数组中的元素都能被访问到.可以重复访问同一个元素.
 *
 * Write a program that takes as arguments a 2D array and a ID array, and checks
 * whether the ID array occurs in the 2D array ???????????????????????
 *
 *
 * Suppose you are given a 2D array of integers (the "grid"), and a ID array of integers
 * (the "pattern"). We say the pattern occurs in the grid if it is possible to start from some
 * entry in the grid and traverse adjacent entries in the order specified by the pattern
 * till all entries in the pattern have been visited.The entries adjacent to an entry are
 * the ones directly (above, below, to the left, and to the right)
 *
 */
public class P7_SearchIn2DArray {

  /**
   * 遍历所有可能的子数组:
   *
   * Its inefficiency stems from not using the target ID subarray to guide the search
   * 没有利用一维模式子数组所提供的信息.
   *
   * @param gird
   * @param pattern
   * @return
   */
  public static boolean bruteForceSolution(List<List<Integer>> gird, List<Integer> pattern) {

    return true;
  }


  /**
   *
   * S是待匹配的一维数组, 如果offset == s.length, 那么匹配就已经结束了。
   * 不是的话,那么pattern　offset开始处的元素要和二维数组起始点的数据相同. 然后再对pattern后面的元素在
   * 二维数组的毗邻处进行匹配.
   *
   * 官方吐槽:
   * Let's say we have a suffix of S to match, and a starting point to match from. If the
   * suffix is empty, we are done. Otherwise, for the suffix to occur from the starting
   * point, the first entry of the suffix must equal the entry at the starting point, and the
   * remainder of the suffix must occur starting at a point adjacent to the starting point.
   *
   *
   * The complexity is O(nm|S|),where n and m are the dimensions of A--we do a constant
   * amount of work within each call to the match function, except for the recursive calls,
   * and the number of calls is not more than the number of entries in the 2D array
   * @param gird
   * @param pattern
   * @return
   */
  public static boolean dpSolution(List<List<Integer>> gird, List<Integer> pattern) {


    Set<Attempt> set = new HashSet<>();

    boolean flag = false;

    outer:
    for (int i = 0; i < gird.size(); i++) {
      for (int j = 0; j < gird.get(i).size(); j++) {
        if (isPatternSuffixInGirdStartXY(gird, i, j, pattern, 0, set)) {
          flag = true;
          // 直接结束外层循环.
          break outer;
        }
      }
    }

    System.out.println(set);

    return flag;

  }

  private static boolean isPatternSuffixInGirdStartXY(
      List<List<Integer>> gird, int x, int y, List<Integer> pattern, int offset, Set<Attempt> set) {

    // 已经到了末尾了.
    if (pattern.size() == offset) {
      return true;
    }

    // Attempt(x, y, offset)表示二维数组中(x, y)开始，匹配pattern offset后缀的已经计算过.
    if (x < 0 || x >= gird.size() || y < 0 || y >= gird.get(x).size()
        || set.contains(new Attempt(x, y, offset))) {
      return false;
    }

    // 当前元素相等并且有一条路径走的通.
    if (gird.get(x).get(y).equals(pattern.get(offset))
        // above
        && ( isPatternSuffixInGirdStartXY(gird, x - 1, y, pattern, offset + 1, set)

        // below
        || isPatternSuffixInGirdStartXY(gird, x + 1, y, pattern, offset + 1, set)

        // left
        || isPatternSuffixInGirdStartXY(gird, x, y - 1, pattern, offset + 1, set)

        // right
        || isPatternSuffixInGirdStartXY(gird, x, y + 1, pattern, offset + 1, set)

        )) {
      return true;
    }

    set.add(new Attempt(x, y, offset));

    return false;
  }

  /**
   *
   * @param gird
   * @param pattern
   * @return
   */
  public static boolean noMoreThanOnceVisitDPSolution(List<List<Integer>> gird, List<Integer> pattern) {
    boolean[][] visited = new boolean[gird.size()][gird.get(0).size()];

    Set<Attempt> attempts = new HashSet<>();

    boolean flag = false;

    for (int i = 0; i < gird.size(); i++) {
      for (int j = 0; j < gird.get(i).size(); j++) {

      }
    }


    return flag;
  }


  public static void main(String[] args) {
    List<List<Integer>> gird = new ArrayList<>();
    gird.add(Arrays.asList(1, 2, 3));
    gird.add(Arrays.asList(4, 5, 6));
    gird.add(Arrays.asList(7, 8, 9));
//    List<Integer> pattern = Arrays.asList(1, 2, 5, 6, 9, 8, 9);

    List<Integer> pattern = Arrays.asList(1, 2, 5, 4, 5, 2, 1);
    System.out.println(dpSolution(gird, pattern));
  }



  private static class Attempt {

    int x;
    int y;
    int offset;

    public Attempt(int x, int y, int offset) {
      this.x = x;
      this.y = y;
      this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Attempt attempt = (Attempt) o;

      if (x != attempt.x) return false;
      if (y != attempt.y) return false;
      return offset == attempt.offset;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      result = 31 * result + offset;
      return result;
    }

    @Override
    public String toString() {
      return "Attempt{" +
          "x=" + x +
          ", y=" + y +
          ", offset=" + offset +
          '}';
    }
  }
}

/*
Variant: Solve the same problem when you cannot visit an entry in A more than once.
Variant: Enumerate all solutions when you cannot visit an entry in A more than once
 */