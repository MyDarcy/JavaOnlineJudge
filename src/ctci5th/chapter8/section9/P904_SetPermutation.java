package ctci5th.chapter8.section9;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author by darcy
 * Date on 17-7-30 上午10:09.
 * Description:
 * 返回集合的所有子集
 */
public class P904_SetPermutation {

  /**
   * k个元素组成的所有子集合是Setk, k+1个元素组成的所有子集合相比k个元素的情况，新增加的元素是：
   * 第k+1个元素 + 原SetK中每一个子集组成的新的子集合
   * P(2) = {} {a1}, {a2}, {a1, a2}  新增加的部分
   * P(3) = {} {a1}, {a2}, {a1, a2}, {a3}, {a1, a3}, {a2, a3}, {a1, a2, a3}
   * @param list
   * @param index
   * @return
   */
  public static ArrayList<ArrayList<Integer>> getSubSets(ArrayList<Integer> list, int index) {
    ArrayList<ArrayList<Integer>> result;
    if (list.size() == index) {
      // 已经遍历到了最后一个元素, 那么直接返回空集合;
      result = new ArrayList<>();
      result.add(new ArrayList<>());
    } else {
      result = getSubSets(list, index + 1);
      Integer element = list.get(index);
      // 因为迭代的过程中不能直接修改result,所以需要一个暂存list存储element + {sets}...组成的集合.
      ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
      for (ArrayList<Integer> var : result) {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.addAll(var);
        newList.add(element);
        temp.add(newList);
      }
      result.addAll(temp);
    }
    return result;
  }

  /**
   * 因为总共有2^n(总共有n个数字,n位可以代表n个数字选择与否)中可能的情况，那么只需要将0...(2^n - 1)映射成为一个set即可, 代表list中每个数字的选择序则与否.
   * @param list
   * @return
   */
  public static ArrayList<ArrayList<Integer>> getSubSets(ArrayList<Integer> list) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    int max = 1 << list.size();
    for (int i = 0; i < max; i++) {
      ArrayList<Integer> temp = convertToSet(i, list);
      result.add(temp);
    }
    return result;
  }

  private static ArrayList<Integer> convertToSet(int value, ArrayList<Integer> list) {
    ArrayList<Integer> temp = new ArrayList<>();
    int index = 0;
    System.out.println(value);
    for (int i = value; i > 0; i >>= 1) {
      // 从左往右统计哪一位, 同时index是从小到大的.
      if ((i & 1) == 1) {
        temp.add(list.get(index));
      }
      index++;
    }
    return temp;
  }

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    list.addAll(Arrays.asList(1, 2, 3));
    ArrayList<ArrayList<Integer>> result = getSubSets(list, 0);
    printSubSets(result);
    System.out.println("----");

    ArrayList<ArrayList<Integer>> result2 = getSubSets(list);
    printSubSets(result2);
  }

  private static void printSubSets(ArrayList<ArrayList<Integer>> result) {
    for (ArrayList<Integer> var : result) {
      System.out.println(var);
    }
  }
}
